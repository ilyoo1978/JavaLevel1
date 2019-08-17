package Lesson6.HW6;

class MainClassString {
    public static void main(String[] args) {

        String str1 = "     Предложение один    Теперь предложение два     Предложение три     ";
        System.out.println(str1);

        String str2 = str1.replaceAll(" +", " ");
        System.out.println(str2);

        StringBuilder stringBuilder = new StringBuilder(str2);

        for(int i = 1; i < str2.length(); i++) {
            if(str2.charAt(0) == ' '){
                stringBuilder.delete(0, 1);
                str2 = stringBuilder.toString();
                i--;
                continue;
            }
            if(str2.charAt(i) >= 'A' && str2.charAt(i) <= 'Я') {
                stringBuilder.insert(i-1, ".");
                str2 = stringBuilder.toString();
                i++;
                continue;
            }
        }
        while(str2.charAt(str2.length()-1) == ' '){
            stringBuilder.delete(str2.length() - 1, str2.length());
            str2 = stringBuilder.toString();
        }
        if(str2.charAt(str2.length()-1) != '.' && str2.charAt(str2.length()-1) != '!' && str2.charAt(str2.length()-1) != '?' && str2.charAt(str2.length()-1) != ';'){
            stringBuilder.append('.');
        }
        System.out.println(stringBuilder.toString());

    }
}
