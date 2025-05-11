# 設計原則

## ①Intro:アプリケーション内の変化する部分を特定し、不変な部分と分離する
目的：変わる部分を見つけて、他の部分から分離することで、変更の影響を局所化する  
悪い例  
```
class Duck {
    void quack() { System.out.println("ガーガー"); }
    void swim() { System.out.println("泳ぐ"); }
    void fly() { System.out.println("飛ぶ"); } // ← 種類によって違う（変化点）
}
```
良い例  
```
interface FlyBehavior {
    void fly();
}

class FlyWithWings implements FlyBehavior {
    public void fly() { System.out.println("羽ばたいて飛ぶ"); }
}

class FlyNoWay implements FlyBehavior {
    public void fly() { System.out.println("飛べない"); }
}

class Duck {
    FlyBehavior flyBehavior;
    void performFly() { flyBehavior.fly(); }
}
```

## ②Intro:実装に対してではなく、インターフェースに対してプログラミングする
目的：具体的なクラス（実装）に依存するのではなく、共通のインターフェースや抽象クラスに依存することで、柔軟な設計が可能  
悪い例  
```
// 悪い例：具体的なクラスに依存
PaymentProcessor processor = new CreditCardProcessor(); // クラス名を知ってる
processor.pay(100);
```
良い例  
```
interface PaymentProcessor {
    void pay(int amount);
}

class CreditCardProcessor implements PaymentProcessor {
    public void pay(int amount) { /* クレジットで支払い */ }
}

class PayPalProcessor implements PaymentProcessor {
    public void pay(int amount) { /* PayPalで支払い */ }
}

void checkout(PaymentProcessor processor) {
    processor.pay(100);
}
```
## ③Intro:継承よりコンポジションの方が好ましい
目的：クラスを拡張するために継承（extends）を多用するのではなく、機能を持ったオブジェクトを「持つ」ようにして再利用する（コンポジション）方が柔軟になる  
悪い例  
```
// NG: 継承を使って通知機能を埋め込む
class User extends Notifier {
    void sendWelcomeMessage() {
        notify("ようこそ！");
    }
}
```
良い例  
```
class User {
    private Notifier notifier;

    public User(Notifier notifier) {
        this.notifier = notifier;
    }

    void sendWelcomeMessage() {
        notifier.notify("ようこそ！");
    }
}
```
## ④Observer:相互にやり取りを行うオブジェクト間には、疎結合設計を使う
例  
```
interface Observer {
    void update(float temperature);
}

class WeatherStation {
    private List<Observer> observers = new ArrayList<>();

    void registerObserver(Observer o) { observers.add(o); }

    void notifyObservers() {
        for (Observer o : observers) {
            o.update(currentTemp);
        }
    }
}
```

## ⑤Decorator：クラスを拡張に対しては開かれた状態にするべきだが、変更に対しては閉じた状態にする
目的：既存のコードに手を加えずに、新しい振る舞いを追加できるようにする
バグを誘発しにくくなり、変更に強いコードになる
他の部分に影響を与えずに、機能拡張を安全に行えるようにする
```
// 図形インターフェース
interface Shape {
    double area();
}

// 具体的な図形クラス
class Circle implements Shape {
    private double radius;
    public Circle(double radius) { this.radius = radius; }
    public double area() { return Math.PI * radius * radius; }
}

class Rectangle implements Shape {
    private double width, height;
    public Rectangle(double width, double height) {
        this.width = width; this.height = height;
    }
    public double area() { return width * height; }
}

// 面積計算クラス（Shapeの種類が増えても変更不要）
class AreaCalculator {
    public double totalArea(List<Shape> shapes) {
        return shapes.stream().mapToDouble(Shape::area).sum();
    }
}
```

## ⑥抽象に依存する。具象クラスに依存してはいけない
目的：クライアント（使う側）は、実装の詳細（＝具象クラス）に依存せず、インターフェース（抽象）に依存せよ

**「上位モジュール（使う側）」は「下位モジュール（具体的な処理）」に依存すべきでない
両者ともに「抽象（インターフェース）」に依存すべき**

ダメな例
```
class EmailService {
    public void sendEmail(String to, String message) {
        System.out.println("Sending email to " + to);
    }
}

class Notification {
    private EmailService emailService = new EmailService(); // ← 具象に依存している

    public void notify(String user, String message) {
        emailService.sendEmail(user, message);
    }
}
```
良い例
```
interface MessageSender {
    void send(String to, String message);
}

class EmailService implements MessageSender {
    public void send(String to, String message) {
        System.out.println("Sending email to " + to);
    }
}

class SmsService implements MessageSender {
    public void send(String to, String message) {
        System.out.println("Sending SMS to " + to);
    }
}

class Notification {
    private MessageSender sender;

    public Notification(MessageSender sender) { // ← コンストラクタで注入
        this.sender = sender;
    }

    public void notify(String user, String message) {
        sender.send(user, message);
    }
}
```




# introduction

## コンポジションの必要性  
あとからの仕様変更・拡張に強い、壊れにくいコードを作るため  

### 抽象化  
意味：本質的な特徴だけを抜き出して、複雑さを隠すこと  
意識すべきこと：利用者に必要な操作だけを提供し、余計な情報は隠す  
```
public interface Animal {
    void speak(); // 何を話すかは具体的な実装に任せる
}
```
### カプセル化  
意味：データとそれを操作するメソッドを一体化し、外部から勝手に変更されないようにすること  
意識すべきこと：データは原則privateにし、必要に応じて公開  
```
public class User {
    private String name;

    public String getName() { return name; }
    public void setName(String name) {
        if (name != null && !name.isBlank()) this.name = name;
    }
}
```
### 多様性  
意味：同じメソッドでも、オンジェクトの型によって異なる振る舞いができること  
意識すべきこと：共通インターフェースや継承元クラスで定義したメソッドを具体クラスごとにオーバーライド  
実行時に適切な動作に分岐できるので、コードの再利用性・柔軟性が高まる  
```
Animal a = new Dog();
a.speak(); // ワンと出力される

a = new Cat();
a.speak(); // ニャーと出力される
```
### 継承  
意味：既存のクラスを拡張して、新しいクラスを作ること  
意識すべきこと：再利用性は高まるが、過剰な継承は密結合・保守困難のもとになる
```
public class Vehicle {
    public void move() { System.out.println("Moving..."); }
}

public class Car extends Vehicle {
    public void honk() { System.out.println("Beep!"); }
}
```  

### 抽象クラス(abstract)を使う場合の基準
- 抽象クラスで具象メソッドを使い回す
- 抽象クラスで共通のフィールドを持たせてサブクラスにも継承したい（interfaceだと実現ができないため）

### interfaceを使う場合の基準
- 共通の契約を定義したいだけ
- 処理はimplementsされた各自で書く（再利用が目的でないとき）
- 柔軟に多重実装したい

# observer
## Oberverパターンとは  
あるオブジェクトの状態が変化すると、そのオブジェクトに依存している全てのオブジェクトに自動的に通知され、更新されるようにするという、オブジェクト感の1対多の依存関係が定義されている    
 
## Observerパターンのメリット  
- 疎結合になる（Observerそれぞれが値を管理する必要がない。Subjectの参照で済む）
- 動的にオブサーバーを追加削除できる
- 再利用性が高い
- 状態変更を自動で伝播
## Observerパターンのデメリット
- 通知の順序やタイミングに依存する可能性
- デバッグが難しくなる
- 循環参照や無限ループの危険
- パフォーマンスの懸念

## Observerパターンを使うべきか判断するための思考の流れ  
1. 何かが変わった時に、誰に伝える必要があるか？  
主役は何か？  
変化を「見張る」必要があるのは誰か  
モデルが更新された時、ビューを自動更新したい    
「状態変化→複数への通知」が自然に出てくるなら候補    
2. 通知先が固定か動的かを確認  
通知する相手が固定 → 単純なコールバックでOKかも  
通知する相手が動的（途中で追加・削除される）→ Observerパターンの出番  
3. 「依存の方向性を一方向に保てるか？」を意識する  
Subject（通知元）は Observer（通知先）を知らなくていい構造にできるのが理想  
→ これにより「疎結合」が実現できる  

# Decorator
## Decoratorパターンの定義
オブジェクトに追加の責務を動的に付与する  
デコレータはサブクラス化の大体となる、柔軟な機能拡張手段を備えている  
例：  
```
Component c = new BorderDecorator(new ScrollDecorator(new TextView()));
```
## Decoratorのメリット
- 機能を動的に追加・変更できる
- 継承の爆発を回避
- オープン/クローズドの原則に従う
- 単一責任の原則に近づけやすい

## Decoratorのデメリット
- 構造が複雑になる
- 型チェックがあしづらい
- 順番依存が発生する可能性
- 初学者が読みにくい

## Decoratorパターン作成時の注意
Decoratorパターンは継承を利用したケースとインターフェースを利用したケースで実現可能である。

### 継承が優れているケース
- 共通のフィールドを持たせたい場合
  - 例えば、description のようなフィールドをすべてのサブクラスで共有したい場合。
  - 抽象クラスにフィールドを定義しておけば、サブクラスで再定義する必要がなくなり、コードの重複を防げます。
- 共通の具象メソッドを提供したい場合
  - 例えば、getDescription() のようなメソッドを抽象クラスで実装しておけば、サブクラスで再実装する必要がありません。
- コードの簡潔さを重視する場合
  - 抽象クラスを使うことで、共通のロジックを一箇所にまとめられるため、コードが簡潔になります。

### インターフェースが優れているケース
- 特定のフィールドや具象メソッドが不要な場合
  - 例えば、description のようなフィールドを持たせる必要がない場合や、共通の具象メソッドが不要な場合。
- 柔軟性を重視する場合
  - インターフェースを使えば、クラスが他のクラスを継承しながら Beverage を実装することが可能です。
    - 例えば、Espresso が別の基底クラス（例えば DrinkBase）を継承しつつ、Beverage を実装することができます。
- 異なる実装を持つクラスを統一的に扱いたい場合
  - インターフェースを使うことで、異なるクラス間で共通の型として扱うことが容易になります。

# Factory
## Factory Methodパターンとは
オブジェクトを作成するためのインターフェースを定義するが、どのクラスをインスタンス化するかについてはサブクラスに決定させる
Factory Methodにより、クラスはサブクラスにインスタンス化を委ねることができる

## SimpleFactoryとFactory Methodの違い
Simple Factory：「製品はここから全部作ってね！」という一括請負型
Factory Method：「作り方は子クラスに任せるね」という方針型の抽象化

#### simpleFactoryの例
製品クラスの生成を1つの静的メソッドに集約するパターン
※ GoFでは正式なパターンではないが、よく使われる

```
class ProductFactory {
    public static Product createProduct(String type) {
        switch (type) {
            case "A": return new ProductA();
            case "B": return new ProductB();
            default: throw new IllegalArgumentException("Unknown type");
        }
    }
}
```

🟢 メリット
- 実装が簡単
- 呼び出す側は new を知らずに済む（疎結合）

🔴 デメリット
- 製品が増えると switch が増えてしまい、OCP（開放閉鎖原則）違反
- 継承や多態性の恩恵がない


#### Factory Methodの例
生成処理をサブクラスに委ねることで拡張性を高めるパターン
```
// 製品インターフェース
interface Product {
    void use();
}

// 各製品
class ProductA implements Product {
    public void use() { System.out.println("Using A"); }
}
class ProductB implements Product {
    public void use() { System.out.println("Using B"); }
}

// Creator 抽象クラス
abstract class Creator {
    public abstract Product createProduct();
}

// 具体的な工場
class ProductACreator extends Creator {
    public Product createProduct() {
        return new ProductA();
    }
}
class ProductBCreator extends Creator {
    public Product createProduct() {
        return new ProductB();
    }
}
```
🟢 メリット
- 新しい製品を追加しても既存コードに手を加えない（OCPを満たす）
- 多態性を活かせる

🔴 デメリット
- 実装がやや複雑でクラスが増える

## 疑問集
### Factory Methodを使うメリット
一言で言うと：
**「何を作るか」と「どう作るか」を分離することで、拡張性・柔軟性・保守性を得るため**
- OCP（開放/閉鎖原則）を守れる
- Creatorが「生成だけ」に責任を持てる（SRPの徹底）
- テストや依存性の注入（DI）がやりやすい
  - 該当のメソッドに対してテスト用のMock（抽象クラスを継承しているMock）を差し込んで動作確認することが可能

#### Factory Methodを利用しないと将来的にどう言うリスクが発生する可能性は？
CreatorとProductを分離しないと、工場の中に製品の知識がハードコードされてしまう

###　高水準コンポーネント
アプリケーションの振る舞いやビジネスロジックを実装する部分。
目的に沿った「何をするか」を定義する。

### 低水準コンポーネント
データベース・ファイル操作・HTTPなど、実際の手段（手足）を担う処理。
「どうやってするか」の詳細。
