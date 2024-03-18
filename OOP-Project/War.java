import java.util.*;

public class War {
    User initialChallenger;
    User initialOpponent;
    User challenger;
    User opponent;
    Vector<Character> challengerArmy;
    Vector<Character> opponentArmy;

    private Vector<Character> challengerArmyAttackingArray;
    private Vector<Character> opponentArmyAttackingArray;
    private Vector<Character> challengerArmyDefendingArray;
    private Vector<Character> opponentArmyDefendingArray;
    private Vector<Character> opponentArmyHealingArray;
    private Vector<Character> challengerArmyHealingArray;

    public War(User initialChallenger,User initialOpponent){
        this.initialChallenger = initialChallenger;
        this.initialOpponent = initialOpponent;
        this.startWar();
    }

    private void initialingArrays(Vector<Character> challengerArmy, Vector<Character> opponentArmy){
        this.challengerArmyAttackingArray = new Vector<>(attackPriorityCheck(challengerArmy));
        this.opponentArmyAttackingArray = new Vector<>(attackPriorityCheck(opponentArmy));
        this.opponentArmyDefendingArray = new Vector<>(defencePriorityCheck(opponentArmy));
        this.challengerArmyDefendingArray = new Vector<>(defencePriorityCheck(challengerArmy));
        this.opponentArmyHealingArray = new Vector<>(opponentArmy);
        this.challengerArmyHealingArray = new Vector<>(challengerArmy);
        this.opponentArmyHealingArray.sort(Comparator.comparingDouble(Character::getHealth));
        this.challengerArmyHealingArray.sort(Comparator.comparingDouble(Character::getHealth));

    }

    public Vector<Character> attackPriorityCheck(Vector<Character> list){
        list.sort(Comparator.comparingDouble(Character::getSpeed)
                .thenComparing(character -> {
                    if (character instanceof Healer) return 1;
                    else if (character instanceof Mage) return 2;
                    else if (character instanceof MythicalCreature) return 3;
                    else if (character instanceof Knight) return 4;
                    else return 5;
                }).reversed());


        return list;
    }

    public Vector<Character> defencePriorityCheck(Vector<Character> list){
        list.sort(Comparator.comparingDouble(Character::getDefence)
                .thenComparing(character -> {
                    if (character instanceof Mage) return 1;
                    else if (character instanceof Knight) return 2;
                    else if (character instanceof Archer) return 3;
                    else if (character instanceof MythicalCreature) return 4;
                    else return 5;
                }));
        return list;
    }

    void startWar(){
        this.challenger = initialChallenger;
        this.opponent = initialOpponent;
        this.challengerArmy = new Vector<>();
        for (Character character : initialChallenger.getArmy()) {
            this.challengerArmy.add(character.clone());
        }

        this.opponentArmy = new Vector<>();
        for (Character character : initialOpponent.getArmy()) {
            this.opponentArmy.add(character.clone());
        }
        for(Character character:challengerArmy){
            switch (initialOpponent.getHomeGround()) {
                case "Hillcrest" -> {
                    if (character.getCharacterType().equals("Highlander")) {
                        character.setAttack(character.getAttack() + 1);
                        character.setDefence(character.getDefence() + 1);

                    }
                    if (character.getCharacterType().equals("Marshlander")) {
                        character.setSpeed(character.getSpeed() - 1);
                    }
                    if (character.getCharacterType().equals("Sunchild")) {
                        character.setSpeed(character.getSpeed() - 1);
                    }
                }
                case "Marshland" -> {
                    if (character.getCharacterType().equals("Marshlander")) {
                        character.setDefence(character.getDefence() + 2);
                    }
                    if (character.getCharacterType().equals("Sunchild")) {
                        character.setAttack(character.getAttack() - 1);
                    }
                    if (character.getCharacterType().equals("Mystic")) {
                        character.setSpeed(character.getSpeed() - 1);
                    }
                }
                case "Desert" -> {
                    if (character.getCharacterType().equals("Marshlander")) {
                        character.setHealth(character.getHealth() - 1);
                    }
                    if (character.getCharacterType().equals("Sunchild")) {
                        character.setAttack(character.getAttack() + 1);
                    }
                }
                case "Arcane" -> {
                    if (character.getCharacterType().equals("Mystic")) {
                        character.setAttack(character.getAttack() + 2);
                    }
                    if (character.getCharacterType().equals("Highlander")) {
                        character.setSpeed(character.getSpeed() - 1);
                        character.setDefence(character.getDefence() - 1);
                    }
                    if (character.getCharacterType().equals("Marshlander")) {
                        character.setSpeed(character.getSpeed() - 1);
                        character.setDefence(character.getDefence() - 1);
                    }
                }
            }
        }

        for(Character character:opponentArmy){
            switch (initialOpponent.getHomeGround()) {
                case "Hillcrest" -> {
                    if (character.getCharacterType().equals("Highlander")) {
                        character.setAttack(character.getAttack() + 1);
                        character.setDefence(character.getDefence() + 1);

                    }
                    if (character.getCharacterType().equals("Marshlander")) {
                        character.setSpeed(character.getSpeed() - 1);
                    }
                    if (character.getCharacterType().equals("Sunchild")) {
                        character.setSpeed(character.getSpeed() - 1);
                    }
                }
                case "Marshland" -> {
                    if (character.getCharacterType().equals("Marshlander")) {
                        character.setDefence(character.getDefence() + 2);
                    }
                    if (character.getCharacterType().equals("Sunchild")) {
                        character.setAttack(character.getAttack() - 1);
                    }
                    if (character.getCharacterType().equals("Mystic")) {
                        character.setSpeed(character.getSpeed() - 1);
                    }
                }
                case "Desert" -> {
                    if (character.getCharacterType().equals("Marshlander")) {
                        character.setHealth(character.getHealth() - 1);
                    }
                    if (character.getCharacterType().equals("Sunchild")) {
                        character.setAttack(character.getAttack() + 1);
                    }
                }
                case "Arcane" -> {
                    if (character.getCharacterType().equals("Mystic")) {
                        character.setAttack(character.getAttack() + 2);
                    }
                    if (character.getCharacterType().equals("Highlander")) {
                        character.setSpeed(character.getSpeed() - 1);
                        character.setDefence(character.getDefence() - 1);
                    }
                    if (character.getCharacterType().equals("Marshlander")) {
                        character.setSpeed(character.getSpeed() - 1);
                        character.setDefence(character.getDefence() - 1);
                    }
                }
            }
        }


        initialingArrays(challengerArmy, opponentArmy);

        Iterator<Character> challengerHealingIterator = challengerArmyHealingArray.iterator();
        while (challengerHealingIterator.hasNext()) {
            Character character = challengerHealingIterator.next();
            if (character instanceof Healer) {
                challengerHealingIterator.remove();
            }
        }

        Iterator<Character> opponentHealingIterator = opponentArmyHealingArray.iterator();
        while (opponentHealingIterator.hasNext()) {
            Character character = opponentHealingIterator.next();
            if (character instanceof Healer) {
                opponentHealingIterator.remove();
            }
        }
        boolean attackingSide = false;
        int turn = 1;
        double previousHealth = opponentArmyDefendingArray.get(0).getHealth();
        int ci = 0;
        int oi = 0;

        System.out.println("\n\n" + initialChallenger.getName() + "  VS  " + initialOpponent.getName() + "\n");

        while (true) {
            if (!attackingSide) {
                System.out.println("Turn " + turn + ": " + initialChallenger.getName());

                if(!(challengerArmyAttackingArray.getFirst() instanceof Healer)) {
                    System.out.println(challengerArmyAttackingArray.get(ci).getName() + " attacks " + opponentArmyDefendingArray.getFirst().getName());

                    double damage = 0.5 * (challengerArmyAttackingArray.get(ci).getAttack()) - 0.1 * (opponentArmyDefendingArray.getFirst().getDefence());

                    opponentArmyDefendingArray.get(0).setHealth(opponentArmyDefendingArray.get(0).getHealth() - damage);

                    System.out.println(opponentArmyDefendingArray.getFirst().getName()+"'s health reduce by " + damage + " by the attack of " + challengerArmyAttackingArray.get(ci).getName());

                    if(initialChallenger.getHomeGround().equals("Hillcrest") && challengerArmyAttackingArray.get(ci).getCharacterType().equals("Highlander")){
                        double bonusDamage = 0.5 * (challengerArmyAttackingArray.get(ci).getAttack()*0.2) - 0.1 * (opponentArmyDefendingArray.get(0).getDefence());

                        opponentArmyDefendingArray.get(0).setHealth(opponentArmyDefendingArray.get(0).getHealth() - bonusDamage);
                    }
                    if (turn != 1){
                        if(initialChallenger.getHomeGround().equals("Arcane") && opponentArmyDefendingArray.get(0).getCharacterType().equals("Mystics")){
                            opponentArmyDefendingArray.get(0).setHealth(previousHealth*1.1);
                        }
                    }
                    System.out.println(opponentArmyDefendingArray.getFirst().getName() + "'s health: " + opponentArmyDefendingArray.getFirst().getHealth());
                    System.out.println(challengerArmyAttackingArray.get(ci).getName() + "'s health: " + challengerArmyAttackingArray.get(ci).getHealth());

                    if(opponentArmyDefendingArray.getFirst().getHealth() <= 0){
                        System.out.println(opponentArmyDefendingArray.getFirst().getName()+" Died!");

                        Character deadCharacter = opponentArmyDefendingArray.getFirst();

                        opponentArmyDefendingArray.remove(deadCharacter);
                        opponentArmyHealingArray.remove(deadCharacter);
                        opponentArmyAttackingArray.remove(deadCharacter);

                        if (opponentArmyAttackingArray.isEmpty()){
                            break;
                        }
                    }

                }
                else{
                    challengerArmyHealingArray.sort(Comparator.comparingDouble(Character::getHealth));;

                    double healvalue = 0.1*(challengerArmyHealingArray.get(0).getAttack());

                    challengerArmyHealingArray.get(0).setHealth(challengerArmyHealingArray.get(0).getHealth() + healvalue);
                    System.out.println(challengerArmyAttackingArray.get(ci).getName() + " heals " + challengerArmyHealingArray.getFirst().getName());
                    System.out.println(challengerArmyHealingArray.getFirst().getName() + " is healed "+challengerArmyHealingArray.get(0).getHealth()+" by " + healvalue);

                    Character healedchar = challengerArmyHealingArray.get(0);

                    for (Character character : opponentArmyDefendingArray) {
                        if(character.getName().equals(healedchar.getName())){
                            character.setHealth(character.getHealth()+healvalue);
                        }
                    }
                    System.out.println(challengerArmyHealingArray.getFirst().getName() + "'s health: " + challengerArmyHealingArray.getFirst().getHealth());
                    System.out.println(challengerArmyAttackingArray.get(ci).getName() + "'s health: " + challengerArmyAttackingArray.get(ci).getHealth());
                }
                attackingSide = true;
                ci++;
                System.out.println("CI incremented " + ci);
            }
            else{
                System.out.println("Turn " + turn + ": " + initialOpponent.getName());

                if(!(opponentArmyAttackingArray.getFirst() instanceof Healer)) {
                    System.out.println(opponentArmyAttackingArray.get(oi).getName() + " attacks " + challengerArmyDefendingArray.getFirst().getName());

                    double dmg = 0.5 * (opponentArmyAttackingArray.get(oi).getAttack()) - 0.1 * (challengerArmyDefendingArray.get(0).getDefence());

                    challengerArmyDefendingArray.get(0).setHealth(challengerArmyDefendingArray.get(0).getHealth() - dmg);

                    System.out.println(challengerArmyDefendingArray.get(0).getName() + "'s health reduce by " + dmg + " by the attack of " + opponentArmyAttackingArray.get(oi).getName());

                    if(initialChallenger.getHomeGround().equals("Hillcrest") && opponentArmyAttackingArray.get(0).getCharacterType().equals("Highlander")){

                        double bonusdmg = 0.5 * (opponentArmyAttackingArray.get(oi).getAttack()*0.2) - 0.1 * (challengerArmyDefendingArray.get(0).getDefence());

                        opponentArmyDefendingArray.get(0).setHealth(opponentArmyDefendingArray.get(0).getHealth() - bonusdmg);
                    }
                    if(turn != 1){
                        if(initialChallenger.getHomeGround().equals("Arcane") && challengerArmyDefendingArray.get(0).getCharacterType().equals("Mystics")){
                            challengerArmyDefendingArray.get(0).setHealth(previousHealth*1.1);
                        }
                    }
                    System.out.println(challengerArmyDefendingArray.getFirst().getName() + "'s health: " + challengerArmyDefendingArray.getFirst().getHealth());
                    System.out.println(opponentArmyAttackingArray.get(oi).getName() + "'s health: " + opponentArmyAttackingArray.get(oi).getHealth());

                    if(challengerArmyDefendingArray.get(0).getHealth() <= 0){
                        System.out.println(challengerArmyDefendingArray.get(0).getName()+" Died!");

                        Character deadCharacter = challengerArmyDefendingArray.getFirst();

                        challengerArmyDefendingArray.remove(deadCharacter);
                        challengerArmyHealingArray.remove(deadCharacter);
                        challengerArmyAttackingArray.remove(deadCharacter);

                        if (challengerArmyDefendingArray.isEmpty()){
                            break;
                        }
                    }
                }
                else{
                    double healvalue = 0.1*(challengerArmyHealingArray.get(0).getAttack());

                    challengerArmyHealingArray.get(0).setHealth(challengerArmyHealingArray.get(0).getHealth()+healvalue);
                    System.out.println(opponentArmyAttackingArray.get(oi).getName() + " heals " + opponentArmyHealingArray.getFirst().getName());
                    System.out.println(opponentArmyHealingArray.getFirst().getName() + " is healed " + opponentArmyHealingArray.get(0).getHealth()+" by " + healvalue);

                    Character healedchar=challengerArmyHealingArray.get(0);

                    for (Character character : challengerArmyDefendingArray) {
                        if(character.getName().equals(healedchar.getName())){
                            character.setHealth(character.getHealth()+healvalue);
                        }
                    }
                    ;
                }
                if (!opponentArmyDefendingArray.isEmpty()) {
                    previousHealth = opponentArmyDefendingArray.get(0).getHealth();
                }
                challengerArmyHealingArray.sort(Comparator.comparingDouble(Character::getHealth));
                opponentArmyHealingArray.sort(Comparator.comparingDouble(Character::getHealth));
                attackingSide = false;
                turn++;
                oi++;
                System.out.println("OI increment " + oi);

            }
            if (oi >= opponentArmyAttackingArray.size()){
                oi = 0;
                System.out.println("OI zerooo");
            }

            if (ci >= challengerArmyAttackingArray.size()){
                ci = 0;
                System.out.println("OI zerooo");
            }

            if(turn > 10){
                System.out.println("WarEnded");
                break;
            }
            System.out.println("challenger left= " + challengerArmyDefendingArray.size());
            System.out.println("opponent left= " + opponentArmyDefendingArray.size());
            System.out.println();
        }

        System.out.println("------Result----------");
        if(opponentArmyAttackingArray.isEmpty()) {
            System.out.println("winner is = " + initialChallenger.getName());
            initialChallenger.setXp(1);
            initialChallenger.setCoins((int) (initialOpponent.getCoins()*0.1));
            initialOpponent.setCoins((int)(-initialOpponent.getCoins()*0.1));
        } else if (challengerArmyAttackingArray.isEmpty()) {
            System.out.println("winner is = "+initialOpponent.getName());
            initialOpponent.setXp(1);
            initialOpponent.setCoins((int) (initialChallenger.getCoins()*0.1));
            initialChallenger.setCoins((int) (-initialChallenger.getCoins()*0.1));
        }
        else{
            System.out.println("War is Draw");
        }
        System.out.println(initialChallenger.getName() + " XP: " + initialChallenger.getXp() + "    gold coins: " + initialChallenger.getCoins());
        System.out.println(initialOpponent.getName() + " XP: " + initialOpponent.getXp() + "    gold coins: " + initialOpponent.getCoins());
    }
}