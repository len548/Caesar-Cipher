
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
// import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.*;
import java.lang.IllegalArgumentException;

import java.util.Arrays;
import java.util.Collection;
import java.lang.StringBuilder;

@RunWith(Parameterized.class)
public class Caesar
{
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                 { "ABCDEFGHIJKLMNOPQRSTUVWXYZ" , "KLMNOPQRSTUVWXYZABCDEFGHIJ"}, { "abcdefghijklmnopqrstuvwxyz", "klmnopqrstuvwxyzabcdefghij" },
                  { "syfxuhjunj", "ciphertext" }, { "xy1z", "hi1j" }, { "huikbj", "result" } });
    }

    @Parameter // first data value (0) is default
    public /* NOT private */ String fInput;

    @Parameter(1)
    public /* NOT private */ String fExpected;

    @Test
    public void testCaesar()
    {
        Caesar c = new Caesar();
        c.setCaesar(10);
        assertThat(c.cipher(fInput), is(fExpected));
        assertThat(c.decipher(fExpected), is(fInput));
    }

    private int n;
    public Caesar(){}
    public void setCaesar(int n)
    {
        if(n < 1 || n > 25) throw new IllegalArgumentException("the number has to be between 1 and 25!");
        this.n = n;
    }
    private char shifter(char c)
    {
        int x = c;
        if(x >= 65 && x <= 90) return  (char)(((x - 65 + n) % 26) + 65);
        else if(x >= 97 && x <= 122) return  (char)((((x - 97) + n) % 26) + 97);
        else throw new IllegalArgumentException("Not English alphabet!");
    }

    public String cipher(String text)
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < text.length(); i++)
        {
            sb.append(shifter(text.charAt(i)));
        }
        return sb.toString();
    }

    private char bringback(char c)
    {
        int x = c;
        if(x >= 65 && x <= 90) return  (char)(((x - 65 - n + 26) % 26) + 65);
        else if(x >= 97 && x <= 122) return  (char)(((x - 97 - n + 26) % 26) + 97);
        else throw new IllegalArgumentException("Not English alphabet!");
    }

    public String decipher(String text)
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < text.length(); i++)
        {
            sb.append(bringback(text.charAt(i)));
        }
        return sb.toString();
    }


}