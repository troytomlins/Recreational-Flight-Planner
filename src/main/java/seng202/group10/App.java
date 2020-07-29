package seng202.group10;

/**
 * Hello world!
 *
 */
public class App
{

    public int zacMethod(int inputInteger) {
        int returnInteger = inputInteger;
        returnInteger += 10;
        return returnInteger;
    }

    public static void main( String[] args )
    {
        App app = new App();
        System.out.println(app.getStartUpMessage());
    }

    /**
     * Get the string "Kia ora, y'all"
     * @return "Kia ora, y'all
     */
    public String getStartUpMessage()
    {
        return "Kia ora, y'all";

    }

    public String goodbyeWorld() {
        return "Goodbye World!";
    }

    /**
     * @author Tom Rizzi
     * @param numb1 Integer to be subtracted from
     * @param numb2 Integer ammount to subtract form numb2
     * @return Difference of integers
     */
    public int intSubtract(int numb1, int numb2) {
        int diff = numb1 - numb2;
        return diff;
    }
    public String troyMethod() {
      return "This is dumb";
    }
}
