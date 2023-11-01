import java.util.Scanner;

public class Nota1 {
    public static void main(String[] args) {
        int nota1, nota2, media;
        Scanner ler = new Scanner(System.in);
        System.out.println("Digite sua 1ª nota");
        nota1 = ler.nextInt();
        System.out.println("Digite sua 2ª nota");
        nota2 = ler.nextInt();
        media = (nota1 + nota2) / 2;
        if (media <= 5) {
            System.out.println("O aluno foi reprovado");
        } else {
            System.out.println("O aluno foi aprovado");
        }
    }
}
