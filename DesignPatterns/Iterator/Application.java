package Iterator;

import java.util.ArrayList;

// The collection interface must declare a factory method for
// producing iterators.
interface SocialNetwork{
    ProfileIterator createFriendsIterator(String profileId);
    ProfileIterator createCoworkersIterator(String profileId);
}

// Each concrete collection is coupled to a set of concrete iterator classes it returns.
class Facebook implements SocialNetwork{

    @Override
    public ProfileIterator createFriendsIterator(String profileId) {
        return new FacebookIterator(this, profileId, "friend");
    }

    @Override
    public ProfileIterator createCoworkersIterator(String profileId) {
        return new FacebookIterator(this, profileId, "coworker");
    }
}

// The common interface for all iterators.
interface ProfileIterator {
    Profile getNext();
    boolean hasNext();
}

class FacebookIterator implements ProfileIterator {
    // The iterator needs a reference to the collection that it traverses.
    private Facebook facebook;
    private String profileId;
    private String type;

    // An iterator object traverses the collection independently
    // from other iterators. Therefore it has to store the iteration state.
    private int currentPosition;
    private ArrayList<Profile> cache;

    public FacebookIterator(Facebook facebook, String profileId, String type){
        this.facebook = facebook;
        this.profileId = profileId;
        this.type = type;
        this.currentPosition = 0;
        this.cache = new ArrayList<Profile>();
    }

    @Override
    public Profile getNext() {
        if (hasNext()){
            Profile profile = cache.get(currentPosition);
            return profile;
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return currentPosition < cache.size();
    }
}

// Dummy implementations for LinkedIn and Profile to make the example complete.
class LinkedIn implements SocialNetwork {
    @Override
    public ProfileIterator createFriendsIterator(String profileId) {
        return new LinkedInIterator(this, profileId, "friends");
    }

    @Override
    public ProfileIterator createCoworkersIterator(String profileId) {
        return new LinkedInIterator(this, profileId, "coworkers");
    }
}

class LinkedInIterator implements ProfileIterator {
    // Similar to FacebookIterator but for LinkedIn
    // The iterator needs a reference to the collection that it traverses.
    private LinkedIn linkedIn;
    private String profileId;
    private String type;

    // An iterator object traverses the collection independently
    // from other iterators. Therefore it has to store the iteration state.
    private int currentPosition;
    private ArrayList<Profile> cache;

    public LinkedInIterator(LinkedIn linkedIn, String profileId, String type){
        this.linkedIn = linkedIn;
        this.profileId = profileId;
        this.type = type;
        this.currentPosition = 0;
        this.cache = new ArrayList<Profile>();
    }

    @Override
    public Profile getNext() {
        if (hasNext()){
            Profile profile = cache.get(currentPosition);
            return profile;
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return currentPosition < cache.size();
    }
}

// Here is another useful trick: you can pass an iterator to a
// client class instead of giving it access to a whole
// collection. This way, you don't expose the collection to the client.

// And there's another benefit: you can change the way the
// client works with the collection at runtime by passing it a
// different iterator. This is possible because the client code
// isn't coupled to concrete iterator classes.

class SocialSpammer{
    public void send(ProfileIterator profileIterator, String msg){
        while (profileIterator.hasNext()){
            Profile profile = profileIterator.getNext();
            System.out.println("Email sending to " + profile.getId());
        }
    }
}


class Profile {
    private String id;
    private String email;

    public Profile(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}


// The application class configures collections and iterators
// and then passes them to the client code.
class Application1 {
    private SocialNetwork network;
    private SocialSpammer spammer;

    public void config(boolean useFacebook) {
        if (useFacebook) {
            this.network = new Facebook();
        } else {
            this.network = new LinkedIn();
        }
        this.spammer = new SocialSpammer();
    }

    public void sendSpamToFriends(Profile profile){
        ProfileIterator iterator = network.createFriendsIterator(profile.getId());
        spammer.send(iterator, "Very important message to friends");
    }
    public void sendSpamToCoworkers(Profile profile) {
        ProfileIterator iterator = network.createCoworkersIterator(profile.getId());
        spammer.send(iterator, "Very important message");
    }
}

public class Application {
    public static void main(String[] args){
        Application1 application1 = new Application1();
        application1.config(true);
        Profile profile = new Profile("a","a@gamail.com");

        application1.sendSpamToFriends(profile);
        application1.sendSpamToCoworkers(profile);
    }
}