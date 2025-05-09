# 設計原則

**①Intro:アプリケーション内の変化する部分を特定し、不変な部分と分離する**  
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

**②Intro:実装に対してではなく、インターフェースに対してプログラミングする**  
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
**③Intro:継承よりコンポジションの方が好ましい**  
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
**④Observer:相互にやり取りを行うオブジェクト間には、疎結合設計を使う** 
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

**⑤Decorator：クラスを拡張に対しては開かれた状態にするべきだが、変更に対しては閉じた状態にする**


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