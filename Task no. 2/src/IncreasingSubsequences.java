    import java.util.ArrayList;
import java.util.List;

    public class IncreasingSubsequences {

        public static List<List<Integer>> findIncreasingSequences(int[] arr) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> current = new ArrayList<>();

            for (int i = 0; i < arr.length; i++) {
                if (current.isEmpty() || arr[i] > current.get(current.size() - 1)) {
                    // ממשיך רצף עולה
                    current.add(arr[i]);
                } else {
                    // הרצף נשבר – אם הוא באורך 2+, שומרים
                    if (current.size() >= 2) {
                        result.add(new ArrayList<>(current));
                    }
                    // מתחילים רצף חדש
                    current.clear();
                    current.add(arr[i]);
                }
            }

            // בדיקה אחרונה אחרי הלולאה
            if (current.size() >= 2) {
                result.add(new ArrayList<>(current));
            }

            return result;
        }

    }
