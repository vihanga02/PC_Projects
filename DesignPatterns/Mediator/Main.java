package Mediator;

// The mediator interface declares a method used by components
// to notify the mediator about various events. The mediator may
// react to these events and pass the execution to other
// components.
interface Mediator0 {
    void notify(Components sender, String event);
}

// The concrete mediator class. The intertwined web of
// connections between individual components has been untangled
// and moved into the mediator.
class AuthenticationDialog implements Mediator0 {
    protected String title;
    protected Checkbox loginOrRegisterChkBx;
    protected Textbox loginUsername, loginPassword;
    protected Textbox registrationUsername, registrationPassword, registrationEmail;
    protected Button okBtn, cancelBtn;

    public AuthenticationDialog() {
        // Create all component objects by passing the current
        // mediator into their constructors to establish links.
        this.loginOrRegisterChkBx = new Checkbox(this);
        this.loginUsername = new Textbox(this);
        this.loginPassword = new Textbox(this);
        this.registrationUsername = new Textbox(this);
        this.registrationPassword = new Textbox(this);
        this.registrationEmail = new Textbox(this);
        this.okBtn = new Button(this);
        this.cancelBtn = new Button(this);
    }

    // When something happens with a component, it notifies the
    // mediator. Upon receiving a notification, the mediator may
    // do something on its own or pass the request to another
    // component.
    @Override
    public void notify(Components sender, String event) {
        if (sender == loginOrRegisterChkBx && event.equals("check")){
            if (loginOrRegisterChkBx.isChecked()) {
                title = "Log in";
                // 1. Show login form components.
                // 2. Hide registration form components.
                showLoginForm();
                hideRegistrationForm();
            }
        }
        else{
            title = "Register";

            showRegistrationForm();
            hideLoginForm();
        }
        if (sender == okBtn && event.equals("click")) {
            if (loginOrRegisterChkBx.isChecked()) {
                // Try to find a user using login credentials.
                boolean found = findUser(loginUsername.getText(), loginPassword.getText());
                if (!found) {
                    // Show an error message above the login field.
                    showError("User not found");
                }
            } else {
                // 1. Create a user account using data from the registration fields.
                createUser(registrationUsername.getText(), registrationPassword.getText(), registrationEmail.getText());
                // 2. Log that user in.
                loginUser(registrationUsername.getText(), registrationPassword.getText());
            }
        }
    }
    private void showLoginForm() {
        System.out.println("Showing login form components.");
        // Show login form components.
    }

    private void hideLoginForm() {
        System.out.println("Hiding login form components.");
        // Hide login form components.
    }

    private void showRegistrationForm() {
        System.out.println("Showing registration form components.");
        // Show registration form components.
    }

    private void hideRegistrationForm() {
        System.out.println("Hiding registration form components.");
        // Hide registration form components.
    }

    private boolean findUser(String username, String password) {
        System.out.println("Finding user with username: " + username);
        // Logic to find user.
        return false; // Dummy return.
    }

    private void showError(String message) {
        System.out.println("Error: " + message);
        // Show error message.
    }

    private void createUser(String username, String password, String email) {
        System.out.println("Creating user with username: " + username);
        // Logic to create user.
    }

    private void loginUser(String username, String password) {
        System.out.println("Logging in user with username: " + username);
        // Logic to log user in.
    }
}

// Components communicate with a mediator using the mediator interface.
abstract class Components{
    protected Mediator0 dialog;
    public Components(Mediator0 dialog){
        this.dialog = dialog;
    }
    public void click() {
        dialog.notify(this, "click");
    }

    public void keypress() {
        dialog.notify(this, "keypress");
    }
}

// Concrete components don't talk to each other. They have only
// one communication channel, which is sending notifications to
// the mediator.
class Button extends Components{
    Button(Mediator0 dialog){
        super(dialog);
    }
}

class Textbox extends Components{
    private String text;

    Textbox(Mediator0 dialog){
        super(dialog);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

class Checkbox extends Components{
    private boolean checked;

    Checkbox(Mediator0 dialog){
        super(dialog);
    }

    public boolean isChecked(){
        return checked;
    }

    public void check(){
        this.checked = !this.checked;
        dialog.notify(this, "check");
    }
}


public class Main {
    public static void main(String[] args) {
        AuthenticationDialog dialog = new AuthenticationDialog();

        // Simulate user interactions.
        dialog.loginOrRegisterChkBx.check(); // Switch to login form
        dialog.okBtn.click(); // Attempt to log in (should show error since user not found)

        dialog.loginOrRegisterChkBx.check(); // Switch to registration form
        dialog.registrationUsername.setText("new_user");
        dialog.registrationPassword.setText("password123");
        dialog.registrationEmail.setText("new_user@example.com");
        dialog.okBtn.click(); // Register and log in the new user
    }
}
