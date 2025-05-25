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

## ⑥最小知識の原則：直接の友達とだけやり取りする
「オブジェクトは、密接に関係する他のオブジェクト（＝“友達”）とだけやり取りするべき」
→ よく知られている別名：Law of Demeter（デメテルの法則）

## ⑦ハリウッドの原則
こちらを呼び出すな。こちらから呼び出す。
“Don't call us, we'll call you.”（「私たちから連絡するから、君からは連絡しないで」）
一言で言うと、
**「下位コンポーネント（部品）から上位コンポーネント（制御側）を呼び出させない」
→ 上位が制御の主導権を持つ構造にする**

目的は「制御の反転（Inversion of Control）」を促すこと：
下位（部品）は 自分の役割だけに集中
上位（制御側）が必要に応じて下位を呼び出す

## 単一責務の原則
**クラスを変更する理由は1つだけにする**
クラスないの変更は避ける。コードの修正は、問題が忍び込む様々な機会を与えることになる。
変更する理由が2つあると、クラスが将来変更される可能性が高くなり、変更があった場合は設計の2つの側面に影響を当てることになる

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

### 委譲
「自分が持っているインスタンスのメソッドを呼んで、処理を任せる」構造

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

# Singleton
## Singletonパターンの定義
クラスがインスタンスを一つしか持たないことを保証し、そのインスタンスにアクセスするグローバルポイントを提供する

## Singletonクラスの注意点
- publicコンストラクタを持たない
  - privateコンストラクタを持つことで、外部からインスタンス化をすることができない
  こうすることで、Singletonクラスが持つstaticのgetInstanceメソッドを経由することでしかsingletonクラスのインスタンス化はできない（getInstanceメソッド内でインスタンス化されているが、getInstanceはSingletonクラス内のため、privateコンストラクタにアクセス可能である。よって、getInstance内ではインスタンス化することが可能となる。）

## SingletonパターンでPrivate Static Synchronizedでインスタンスを定義するわけ
### privateの理由
- 外部から直接アクセスを防ぐため
- カプセル化を維持するため

### staticの理由
static:クラス全体で一つのインスタンスを共有し、グローバルにアクセス可能にするため
- クラス全体で共有するため
- インスタンスをグローバルにアクセス可能にするため
- getInstanceメソッドからアクセス可能にするため
  - このメソッドがstaticであるため、クラスのインスタンスを生成せずに呼び出すことが可能。内部で呼び出されているuniqueinstanceもstaticである必要がある

### Synchroonizedの理由
- 同時アクセスの防止
  - 複数スレッドで同時にgetInstance()を呼び出すとnullチェックを複数のスレッドが同時に通過し、それぞれが新しいインスタンスを生成してしまう可能性がある
  - synchronizedを使うことで一つのスレッドがこのメソッドを実行している間、他のスレッドは待機するようになる

## enum型を使ったsingletonの簡易実装
### enum型とは
列挙型と呼ばれ、あらかじめ定義された定数の集まりを表現する型

### enum でシングルトンを実装できる理由
- JVMが1インスタンスしか生成しない
- 自動的にスレッドセーフ
- シリアライズも安全
- リフレクションにも強い

#### 用語
**JVM** ：Javaバイトコードを実行する「仮想的なマシン（ソフトウェア）」
**シリアライズ** ；Javaオブジェクト → バイト列に変換する仕組み（＝保存・送信用）。オブジェクトの状態をファイルに保存したり、ネットワークで送信したりするため
**リフレクション**　；Javaクラスの情報（フィールドやメソッドなど）を実行時に操作・参照できる機能


# Command
## Commandパターンとは
リクエストをオブジェクトとしてカプセル化し、その結果、他のオブジェクトをさまざまなリクエスト、キュー、またはログリクエストでパラメータ化でき、アンドゥ可能な操作もサポートする
Commandパターンは、処理（命令）をオブジェクトとして抽象化・分離し、柔軟な実行制御を可能にするデザインパターンです。
「何をするか」を呼び出し側（Invoker）から独立させ、あとで実行、取り消し、記録、順番制御などができるようになります。

## Commandパターン採用の思考フロー
1. 呼び出す処理が複数存在するか？
2. 呼び出し側と処理内容を疎結合にしたいか？
3. 処理を「後で実行」「蓄積」「繰り返し」したいか？

### 判断基準
処理を「カプセル化」したい	✅ 採用すべき
処理の「実行タイミングを制御」したい	✅ 採用すべき
呼び出し元を「疎結合」にしたい	✅ 採用すべき
処理内容がほとんど固定で拡張予定なし	❌ 過剰設計かも

### 効果的な例
- GUIアプリケーションで、各ボタンに異なる処理を割り当てたい
- テキストエディタなどで、Undo/Redo を実装したい
- ゲームなどでユーザーの行動を記録して、リプレイ再生したい
- サーバーアプリケーションで、処理リクエストを順番にキューで処理したい
- バッチ処理などで、一定時間ごとに溜まったタスクを実行したい


#### 用語
**関数型interface**：抽象メソッドを一つだけ持つインターフェース
```
@FunctionalInterface
public interface MyFunction {
    void run();  // 抽象メソッドは1つだけ
}

public class Main {
    public static void main(String[] args) {
        MyFunction func = () -> System.out.println("Hello from lambda!");
        func.run();
    }
}
```

**Client**：CommandやReceiverを生成し、Invokerに設定する。（組み立てる側）
＝初期構成をする利用者
```
public class Main {
    public static void main(String[] args) {
        // Receiver
        Light light = new Light();

        // Command
        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);

        // Invoker
        RemoteControl remote = new RemoteControl();

        // Commandをセットして実行
        remote.setCommand(lightOn);
        remote.pressButton(); // → 電気をつけました

        remote.setCommand(lightOff);
        remote.pressButton(); // → 電気を消しました
    }
}
```
**Command**：処理（命令）をカプセル化するインターフェース/クラス。（インターフェース）
Receiverへの処理呼び出しをラップする
```
// Command インターフェース
public interface Command {
    void execute();
}
```
**Invoker**：Commandの実行を指示する。（命令の実行者）
ユーザーの操作やイベントに反応してCommandを呼び出す
```
// Invoker
public class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}

```
**Receiver**：実際のビジネスロジックを持つ処理対象。（実際の処理を行う）
Commandから呼び出される
```
// Receiver
public class Light {
    public void turnOn() {
        System.out.println("電気をつけました");
    }

    public void turnOff() {
        System.out.println("電気を消しました");
    }
}
```

# Adapter
## Adapterパターンとは
クラスのインターフェースをクライアントが要求する別のインターフェースに変換する
アダプタは、インターフェースのが完成がないためにそのままでは連携できないクラスを連携させる

Adapterパターンでは、変換を行うアダプタを作成すると、互換性のないインターフェースを持つクライアントを使用する

## Adapterパターンを構成するコンポーネント

#### Target
クライアントが使いたいと思っている共通インターフェース
```
public interface Target {
    void request();  // クライアントが呼び出したいメソッド
}
```

#### Adaptee
すでに存在するが、インターフェースが異なるクラス
直接はクライアントから使えない（メソッド名が違う、引数の型が違う、など）
```
public class Adaptee {
    public void specificRequest() {
        System.out.println("Adaptee の specificRequest() を呼び出しました");
    }
}
```

#### Adapter
AdapteeとTargetの橋渡しをするクラス
クライアントがTargetとして呼び出すと、内部でAdapteeの処理を呼び出す
```
public class Adapter implements Target {
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        // インターフェースが異なる呼び出しを変換
        adaptee.specificRequest();
    }
}
```

#### Client
Targetインターフェースしか知らない
Adapter経由でAdapteeを間接的に利用している 
```
public class Main {
    public static void main(String[] args) {
        Target target = new Adapter(new Adaptee());
        target.request();  // 実際には Adaptee の specificRequest が呼ばれる
    }
}
```

# Facade
## Facadeパターンとは
サブシステムの一連のインターフェースに対する、統合されたインターフェースを提供する。
ファサードは、サブシステムを使いやすくする高水準インターフェースを定義する。

わかりやすくイメージ
たくさん選択肢がある中で、クライアントが使いやすい単位でメソッドとして切り出す


## 採用に適した具体的な状況
- ライブラリが多数のコンポーネントに分かれている	
- 開発者が各部品の使い方を理解するのが大変	
- 外部に実装詳細を漏らしたくない	
- 将来の変更に備えてインターフェースを固定したい	
- テストやモックを簡単にしたい	

## Facadeパターンを導入するメリット
**複雑さのカプセル化**：内部の複雑な処理を隠す
**使いやすさ**：クライアントが使う窓口を統一
**保守性向上**：内部変更しても外部への影響が少ない
**チーム開発での分離**：中の担当と外の利用者を分離できる

## Facadeを使う上での注意点
- Facadeが肥大化する
- 内部の複雑化は依然として存在
- 設計改善になっていない


# Template Method
## Template Methodパターンとは
メソッド内で会うr語リズムの骨組みを定義し、一部の手順をサブクラスに委ねる。
Template Methodは、アルゴリズムの構造を変えることなく、アルゴリズムのある手順をサブクラスに再定義させる。

####　用語
**ヘルパーメソッド**：他のメソッドを補助するための小さな処理を担うメソッド


# Iterator
## Iteratorパターンとは
内部表現を公開せずに、アグリゲートオブジェクトの要素に順次アクセスする方法を提供する



### 疑問
異なる形式のコレクションに対して同様の振る舞いを持たせたいパターンはそう多くない気がする
#### 用語
**アグリゲート**：要素を格納しているコレクションの本体。イテレータを生成する役割を持つ

```
public interface Aggregate {
    Iterator createIterator();
}

public class BookShelf implements Aggregate {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public Book getBookAt(int index) {
        return books.get(index);
    }

    public int getLength() {
        return books.size();
    }

    @Override
    public Iterator createIterator() {
        return new BookShelfIterator(this);
    }
}

```

# Composite
## Compositeパターンとは
オブジェクトをツリー構造に構成して部分 - 全体階層を表現できる。Compositeパターンを使うと、クライアントはここのオブジェクトとオブジェクトコンポジションを統一的に扱うことができる。
「個々のオブジェクト（葉）と、それらをまとめるオブジェクト（枝）を同一視して扱うためのパターン」

## どのような時に利用するか
階層構造（ツリー構造）を扱うとき
再帰的な構造を「親も子も同じインターフェースで扱いたい」とき


           Component（共通インターフェース）
                  ▲
          ┌──────┴──────┐
       Leaf（File）   Composite（Directory）

| クラス名          | 役割               | 具体的な説明                                                 |
| ------------- | ---------------- | ------------------------------------------------------ |
| **Component** | 抽象クラス / インターフェース | LeafやCompositeに共通の操作（メソッド）を定義する。例：`draw()`、`show()`など。 |
| **Leaf**      | ツリーの「末端ノード」      | 子を持たない要素。ファイル、ボタン、社員など。実際の処理を実装する。                     |
| **Composite** | ツリーの「中間または根ノード」  | 自分と同じComponent（Leafや他のComposite）を複数保持して管理する。           |


## 判断基準まとめ（YESなら採用）
質問	YES？
構造が再帰的（ツリー状）ですか？	✅
部分と全体を同じ操作で扱いたいですか？	✅
新しい構成要素を柔軟に追加したいですか？	✅
操作をルートから一括で行いたいですか？	✅

## ユースケース
- ファイルシステムの管理 : フォルダとファイルを同じように操作したい
- UI部品のツリー構造 : ボタン、パネルなどがネストしてUIツリーを構成
- 組織図の管理 : 上司と部下のツリー構造を一括操作
- ゲームオブジェクトの構成 : 親オブジェクトが子オブジェクトを持ち、同時に更新や描画
- XML : HTML DOM構造の操作	要素の中に要素が入るツリーを再帰的に走査
- 式の評価（AST） : 数式（例：1 + (2 * 3)）をツリーとして評価
- 商品や部品の構成（BOM） : 製品が部品を含み、その部品もまた部品を持つ