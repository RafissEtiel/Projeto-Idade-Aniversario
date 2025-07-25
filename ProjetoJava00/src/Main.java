import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.DateTimeException;
import java.util.Scanner;
 
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int anoNasc = lerCampo(scanner, "ano", "\\d{4}");
        int mesNasc = lerCampo(scanner, "mês", "\\d{2}");
        int diaNasc = lerCampo(scanner, "dia", "\\d{2}");

        try {
            LocalDate dataNascimento = LocalDate.of(anoNasc, mesNasc, diaNasc);
            LocalDate hoje = LocalDate.now();

            Period idade = Period.between(dataNascimento, hoje);
            System.out.println("\nVocê tem " + idade.getYears() + " anos.");

            LocalDate aniversarioEsteAno = LocalDate.of(hoje.getYear(), mesNasc, diaNasc);
            LocalDate proximoAniversario = aniversarioEsteAno.isBefore(hoje)
                    ? aniversarioEsteAno.plusYears(1)
                    : aniversarioEsteAno;

            System.out.println("Seu próximo aniversário será em: " + proximoAniversario);


            System.out.println("\nDeseja saber quantos dias faltam para o seu aniversário?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            String escolha = scanner.nextLine();

            if (escolha.equals("1")) {
                long diasFaltando = ChronoUnit.DAYS.between(hoje, proximoAniversario);
                System.out.println("Faltam " + diasFaltando + " dias para o seu próximo aniversário! ");
            } else {
                System.out.println("Atendimento finalizado!");
            }

        } catch (DateTimeException e) {
            System.out.println("\nData inválida! Verifique se os valores digitados correspondem a uma data real.");
        }
    }

    public static int lerCampo(Scanner scanner, String campo, String padrao) {
        while (true) {
            System.out.print("Digite o " + campo + " do seu nascimento: ");
            String entrada = scanner.nextLine();
            if (entrada.matches(padrao)) {
                return Integer.parseInt(entrada);
            } else {
                System.out.println("Entrada inválida! Digite " +
                        (campo.equals("ano") ? "4" : "2") + " números, sem letras ou símbolos.");
            }
        }
    }
}
