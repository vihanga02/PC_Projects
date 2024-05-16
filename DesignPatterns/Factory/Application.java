package Factory;

import java.util.*;

// this is the factory class and this is a abstract one
// so its creation method implementations are implemented in concrete creator classes,
abstract class Dialog{
    abstract Buttons createButton();

    public void render(){
        // get the product via concrete factory classes implementations
        Buttons okButton = createButton();

        // call the methods in concrete product classes via product interface
        okButton.render();
        okButton.onClick();
    }
}

// Concrete windows dialog class returns the windows button
class WindowsDialog extends Dialog {
    @Override
    Buttons createButton() {
        return new WindowsButton();
    }
}

// concrete html dialog class return the html button
class HtmlDialog extends Dialog{
    @Override
    Buttons createButton() {
        return new HtmlButton();
    }
}


// product interface
interface Buttons{
    void render();
    void onClick();
}

// concrete button classes implement button interface and give the corresponding implementations
class WindowsButton implements Buttons{
    @Override
    public void render() {
        System.out.println("Rendering Windows Button.");
    }

    @Override
    public void onClick() {
        System.out.println("Action started on Windows Button.");
    }
}

// concrete button classes implement button interface and give the corresponding implementations
class HtmlButton implements Buttons{
    @Override
    public void render() {
        System.out.println("Rendering HTML Button.");
    }

    @Override
    public void onClick() {
        System.out.println("Action started on HTML Button.");
    }
}

public class Application {
    // creating a dialog object corresponds to the input.
    static Dialog dialog;

    static void initialization(){
        Scanner scanner = new Scanner(System.in);
        String os = scanner.next();

        if (os.equals("windows")){
            dialog = new WindowsDialog();
        }
        else{
            dialog = new HtmlDialog();
        }
        scanner.close();
    }

    // client only works with the corresponding factory object and call its methods
    public static void main (String[] args){
        initialization();
        dialog.render();
    }
}
