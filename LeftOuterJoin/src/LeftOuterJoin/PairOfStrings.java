/**
 * Created by winn on 17/5/17.
 */
package LeftOuterJoin;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class PairOfStrings implements Writable, WritableComparable<PairOfStrings>{
	/**在hadoop中，如果需要持久存储定制数据类型，必须实现Writable接口，如果要比较定制数据类型，
	 * 还需要实现WritableComparable接口
	 */
    private Text left = new Text();
    private Text right = new Text();

    public PairOfStrings(){
    }

    public PairOfStrings(String left, String right){
        this.left.set(left);
        this.right.set(right);
    }
    public static PairOfStrings read(DataInput in) throws IOException{
        PairOfStrings pair = new PairOfStrings();
        pair.readFields(in);
        return pair;
    }

    @Override
    public void write(DataOutput out) throws IOException{
        left.write(out);
        right.write(out);
    }
    @Override
    public void readFields(DataInput in) throws IOException{
        left.readFields(in);
        right.readFields(in);
    }

    @Override
    public int compareTo(PairOfStrings pair){
        int compareValue = this.left.compareTo(pair.getLeft());
        if (compareValue == 0){
            compareValue = right.compareTo(pair.getRight());
        }
        return -1 * compareValue;
    }

    public Text getLeft(){
        return left;
    }
    public Text getRight(){
        return right;
    }
    public void set(String leftValue, String rightValue){
        left.set(leftValue);
        right.set(rightValue);
    }

    public void setLeft(String leftAsString){
        left.set(leftAsString);
    }
    public void setRight(String rightAsString){
        right.set(rightAsString);
    }

    @Override
    public int hashCode(){
        int result = left != null ? left.hashCode() : 0;
        result = result + (right != null? right.hashCode(): 0);
        return result;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("PairOfStrings{right=");
        builder.append(right);
        builder.append(",left=");
        builder.append(left);
        builder.append("}");
        return builder.toString();
    }
}
