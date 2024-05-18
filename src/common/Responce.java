package common;

import java.io.Serializable;

public class Responce implements Serializable {
    StringBuilder content = new StringBuilder("Ответ сервера:\n");

    public Responce(){}

    public void addString(String str) {
        content.append(str != null ? str : " ").append("\n");
    }

    public String getContent() {
        return content.toString();
    }
}
