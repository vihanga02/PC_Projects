// This interface ensures that every expression has its
// own interpreter
interface Expression {
     public boolean interpreter(String context);
}
// This is a leaf-level expression that evaluate values
// and in this instance returns a boolean value
class TerminalExpression implements Expression {
  private String data;
  public TerminalExpression(String data) { this.data = data; }
  public boolean interpreter(String context) {
  if(context.contains(data))
    return true;
  else
    return false;
  }
}

// This is a composite expression that evaluate other
// expressions and specifically OR condition
class ORExpression implements Expression {
 private Expression left, right;
 public ORExpression(Expression left, Expression right) {
  this.left = left; this.right = right;
}
 public boolean interpreter(String context) {
   return
    left.interpreter(context) || right.interpreter(context);
 }
}

// This is a composite expression that evaluate other
// expressions and specifically AND condition
class ANDExpression implements Expression {
 private Expression left, right;
 public ANDExpression(Expression left, Expression right) {
  this.left = left; this.right = right;
}
 public boolean interpreter(String context) {
   return
    left.interpreter(context) && right.interpreter(context);
 }
}

// This is a composite expression that evaluate another
// expression and specifically NOT condition
class NOTExpression implements Expression {
 private Expression exp;
 public NOTExpression(Expression exp) {
  this.exp = exp;
}
 public boolean interpreter(String context) {
   return !exp.interpreter(context);
} }

public class ExpressionEvaluator1 {
 public static void main(String [] args) {
  // These are the symbols/terminal
  Expression port1 = new TerminalExpression("HDMI");
  Expression port2 = new TerminalExpression("VGA");
  Expression adapter = new TerminalExpression("HDM2VG");
  // These are the syntax/grammar rules
  Expression isVideoOutput1 = new ORExpression(port1,port2);
  Expression isVideoOutput2 = new ANDExpression(port1,adapter);
  Expression isVideoOutput3 = new NOTExpression(adapter);

   // we will check if given ports give a video output,
  // that is - evaluation/interpretation
  System.out.println(isVideoOutput1.interpreter
                     ("Will I get a video output with HDMI"));
  System.out.println(isVideoOutput1.interpreter("VGA"));
  System.out.println(isVideoOutput1.interpreter("HDM2VG"));
  System.out.println(isVideoOutput2.interpreter("HDMI,HDM2VG"));
  System.out.println(isVideoOutput2.interpreter("VGA,HDM2VG"));
  System.out.println(isVideoOutput2.interpreter("HDM2VG"));
  System.out.println(isVideoOutput3.interpreter("HDMI"));
  System.out.println(isVideoOutput3.interpreter("VGA"));
  System.out.println(isVideoOutput3.interpreter("HDM2VG"));
} }
