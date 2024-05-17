package ChainResponsibility;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// The handler interface declares a method for executing a request.
interface ComponentWithContextualHelp {
    void showHelp();
}

// The base class for simple components.
abstract class Component implements ComponentWithContextualHelp {
    protected String tooltipText;

    // The component's container acts as the next link in the chain of handlers.
    protected Container container;

    // The component shows a tooltip if there's help text assigned to it.
    // Otherwise it forwards the call to the container, if it exists.
    @Override
    public void showHelp() {
        if (tooltipText != null) {
            // Show tooltip
            System.out.println("Tooltip: " + tooltipText);
        } else {
            if (container != null) {
                container.showHelp();
            }
        }
    }

    // Setter for container
    public void setContainer(Container container) {
        this.container = container;
    }

    // Setter for tooltipText
    public void setTooltipText(String tooltipText) {
        this.tooltipText = tooltipText;
    }
}

// Containers can contain both simple components and other
// containers as children. The chain relationships are established here.
abstract class Container extends Component {
    protected List<Component> children = new ArrayList<>();

    public void add(Component child) {
        children.add(child);
        child.setContainer(this);
    }
}

// Primitive components may be fine with default help implementation...
class Button extends Component{
    private int x, y, width, height;
    private String text;

    Button(int x, int y, int width, int height,String text){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
    }
}

// If the help text can't be provided in a new
// way, the component can always call the base implementation
class Panel extends Container{
    private int x, y, width, height;
    private String modalHelpText;

    public Panel(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public void setModalHelpText(String modalHelpText) {
        this.modalHelpText = modalHelpText;
    }

    @Override
    public void showHelp() {
        if (modalHelpText != null) {
            // Show a modal window with the help text.
            System.out.println("Modal help text: " + modalHelpText);
        } else {
            super.showHelp();
        }
    }
}

class Dialog extends Container {
    private String wikiPageURL;
    private String title;

    public Dialog(String title) {
        this.title = title;
    }

    public void setWikiPageURL(String wikiPageURL) {
        this.wikiPageURL = wikiPageURL;
    }

    @Override
    public void showHelp() {
        if (wikiPageURL != null) {
            // Open the wiki help page.
            System.out.println("Opening wiki page: " + wikiPageURL);
        } else {
            super.showHelp();
        }
    }
}

// Client code.
class Application {
    private Dialog dialog;

    // Every application configures the chain differently.
    public void createUI() {
        dialog = new Dialog("Budget Reports");
        dialog.setWikiPageURL("http://example.com/wiki/Budget_Reports");

        Panel panel = new Panel(0, 0, 400, 800);
        panel.setModalHelpText("This panel does...");

        Button ok = new Button(250, 760, 50, 20, "OK");
        ok.setTooltipText("This is an OK button that...");

        Button cancel = new Button(320, 760, 50, 20, "Cancel");

        panel.add(ok);
        panel.add(cancel);
        dialog.add(panel);
    }
    // Simulate getting a component at mouse coordinates
    public Component getComponentAtMouseCoords() {
        // This is a placeholder for actual GUI logic
        // Returning `ok` button for demonstration purposes
        for (Component child : dialog.children) {
            if (child instanceof Panel) {
                Panel panel = (Panel) child;
                for (Component btn : panel.children) {
                    if (btn instanceof Button) {
                        return btn;
                    }
                }
            }
        }
        return dialog;  // fallback if nothing found
    }
    // Imagine what happens here.
    public void onF1KeyPress() {
        Component component = this.getComponentAtMouseCoords();
        component.showHelp();
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.createUI();
        app.onF1KeyPress();
    }
}