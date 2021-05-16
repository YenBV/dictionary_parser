import com.app.dictionary.util.WordUtils;
import org.junit.jupiter.api.Test;

public class Delete {
    @Test
    public void delete() {
        System.out.println(WordUtils.getNgramsForWord("Абітурієнт"));
        System.out.println(WordUtils.getNgramsForWord("Аб іт ур іє н т"));
        System.out.println(WordUtils.getNgramsForWord("Абіт/урі/єнт"));
        System.out.println(WordUtils.getNgramsForWord("А1б2і3т4у5р6і9є7н8т"));
        System.out.println(WordUtils.getNgramsForWord("Абіту_рієнт"));
    }
}
