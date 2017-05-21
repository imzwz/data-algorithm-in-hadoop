package RelativeFrequence;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by winn on 17/5/21.
 */
public class PairOfWords implements WritableComparable<PairOfWords>{
    private String leftElement;
    private String rightElement;

    public PairOfWords(){
    }
    public PairOfWords(String left, String right){
        this.leftElement = left;
        this.rightElement = right;
    }

    @Override
    public void readFields(DataInput in) throws IOException{
        leftElement = Text.readString(in);
        rightElement = Text.readString(in);
    }

    @Override
    public void write(DataOutput out) throws IOException{
        Text.writeString(out, leftElement);
        Text.writeString(out, rightElement);
    }

    public void setLeftElement(String leftElement){
        this.leftElement = leftElement;
    }

    public void setWord(String leftElement){
        setLeftElement(leftElement);
    }

    public String getWord(){
        return leftElement;
    }

    public String getLeftElement(){
        return leftElement;
    }

    public void setRightElement(String rightElement){
        this.rightElement = rightElement;
    }

    public void setNeighbor(String rightElement){
        setRightElement(rightElement);
    }

    public String getRightElement(){
        return rightElement;
    }
    public String getNeighbor(){
        return rightElement;
    }

    public String getKey(){
        return leftElement;
    }

    public String getValue(){
        return rightElement;
    }

    public void set(String left, String right){
        leftElement = left;
        rightElement = right;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(!(obj instanceof PairOfWords)){
            return false;
        }
        PairOfWords pair = (PairOfWords) obj;
        return leftElement.equals(pair.getLeftElement()) && rightElement.equals(pair.getRightElement());
    }

    @Override
    public int compareTo(PairOfWords pair){
        String pl = pair.getLeftElement();
        String pr = pair.getRightElement();
        if(leftElement.equals(pl)){
            return rightElement.compareTo(pr);
        }
        return leftElement.compareTo(pl);
    }

    @Override
    public int hashCode(){
        return leftElement.hashCode() + rightElement.hashCode();
    }

    @Override
    public String toString(){
        return "(" + leftElement + ", " + rightElement + ")";
    }

    @Override
    public PairOfWords clone(){
        return new PairOfWords(this.leftElement, this.rightElement);
    }
/*
    public static class Comparator extends WritableComparator{
        public Comparator{
            super(PairOfWords.class);
        }

        @Override
        public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2){
            try{
                int cmp = compareBytes(b1, s1,l1, b2,s2, l2);
                if(cmp != 0){
                    return cmp;
                }
            }catch (IOException e){
                throw new IllegalArgumentException(e);
            }
        }
    }
    static {
        WritableComparator.define(PairOfWords.class, new Comparator());
    }
    */
}
