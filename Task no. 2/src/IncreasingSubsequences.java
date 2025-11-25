    import java.util.ArrayList;
import java.util.List;

    public class IncreasingSubsequences {

        //function the get arr with numbers
        //check how many  sequences are there
        public static List<List<Integer>> findIncreasingSequences(int[] arr) {
            //initialize the variable
            //save the sequences that found
            List<List<Integer>> result = new ArrayList<>();
            //save the current sequences
            List<Integer> current = new ArrayList<>();

            //Going through the array
            //
            for (int i = 0; i < arr.length; i++) {
                //If current is empty or the number is greater than the last element -> add it to the current sequence.
                if (current.isEmpty() || arr[i] > current.get(current.size() - 1)) {
                    current.add(arr[i]);
                } else {
                    //If the number is smaller or equal -> save current and start a new sequence.
                    if (current.size() >= 2) {
                        result.add(new ArrayList<>(current));
                    }
                    current.clear();
                    current.add(arr[i]);
                }
            }

            if (current.size() >= 2) {
                result.add(new ArrayList<>(current));
            }

            return result;
        }

    }
