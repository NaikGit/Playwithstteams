package io.techwoman;

public class WordCounter {

    public static final String SENTENCE =
            " Nel   mezzo del cammin  di nostra  vita " +
                    "mi  ritrovai in una  selva oscura" +
                    " che la  dritta via era   smarrita ";

    private final int counter;
    private boolean lastSpace;

    public WordCounter(int counter , boolean lastSpace){
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    public WordCounter accumulate(Character c){
        if(Character.isWhitespace(c)){
            return lastSpace ? this : new WordCounter(counter , true);
        }else{
            return lastSpace ? new WordCounter(counter+1, false) : this;
        }
    }

    public WordCounter combine(WordCounter wordCounter){
        return new WordCounter(counter + wordCounter.counter ,wordCounter.lastSpace);
    }

    public int getCounter(){
        return counter;
    }

    public static void main(String[] args) {
        System.out.println("Found " + countWordsIteratively(SENTENCE) + " words");
       // System.out.println("Found " + countWords(SENTENCE) + " words");
    }

    private static int countWordsIteratively(String sentence) {
        int count = 0;
        boolean lastSpace = true;

        System.out.println("Char array length :" + sentence.toCharArray().length);
        for (char c : sentence.toCharArray()) {
            System.out.println("Char array :" + c);
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace)
                    count++;
                lastSpace = false;
            }
        }
        return count;
    }

}
