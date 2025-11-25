import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        int[] arr = {4,3,2,1};
        List<List<Integer>> sequences = IncreasingSubsequences.findIncreasingSequences(arr);

        for (List<Integer> seq : sequences)
            System.out.println(seq);

    }
}