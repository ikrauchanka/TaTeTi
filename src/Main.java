import java.util.Scanner;

public class Main {

    static char[][] matriz = new char[3][3];
    static Scanner scanner = new Scanner(System.in);


    // inicializar el tablero con '_'
    public static void inicializarMatriz() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matriz[i][j] = '_';
            }
        }
    }

    // mostrar actual estado del tablero
    public static void mostrarMatriz() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    // turno del jugador humano
    public static void jugadorUsuario() {
        int fila, columna;
        while (true) {
            System.out.println("es tu turno. Ingresa fila y columna (0, 1, 2) separados por espacio: ");
            // fila
            if (scanner.hasNextInt()) {
                fila = scanner.nextInt();
            } else {
                System.out.println("Error: La entrada para 'fila' no es un número entero.");
                scanner.next();
                return;
            }
            // columna
            if (scanner.hasNextInt()) {
                columna = scanner.nextInt();
            } else {
                System.out.println("Error: La entrada para 'columna' no es un número entero.");
                scanner.next();
                return;
            }
            if (fila >= 0 && fila < 3 && columna >= 0 && columna < 3 && matriz[fila][columna] == '_') {
                matriz[fila][columna] = 'X';
                break;
            } else {
                System.out.println("su movimiento es invalido, por favor ingresar de nuevo.");
            }
        }
    }

    // turno de la computadora
    public static void jugadorComputadora() {
        int fila, columna;
        while (true) {
            fila = (int) (Math.random() * 3);
            columna = (int) (Math.random() * 3);

            if (matriz[fila][columna] == '_') {
                matriz[fila][columna] = 'O';
                System.out.println("la computadora coloca 'O' en la fila " + fila + " y columna " + columna);
                break;
            }
        }
    }

    // verifica si el jugador gano
    public static boolean verificarGanador(char jugador) {
        // verificar filas, columnas y diagonales
        for (int i = 0; i < 3; i++) {
            if ((matriz[i][0] == jugador && matriz[i][1] == jugador && matriz[i][2] == jugador) ||
                    (matriz[0][i] == jugador && matriz[1][i] == jugador && matriz[2][i] == jugador)) {
                mostrarMatriz();
                System.out.println("el jugador " + jugador + " gano!");
                return true;
            }
        }
        if ((matriz[0][0] == jugador && matriz[1][1] == jugador && matriz[2][2] == jugador) ||
                (matriz[0][2] == jugador && matriz[1][1] == jugador && matriz[2][0] == jugador)) {
            mostrarMatriz();
            System.out.println("el jugador " + jugador + " gano!");
            return true;
        }
        return false;
    }

    // verifica si empate (todas las celdas están llenas con O y X)
    public static boolean esEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matriz[i][j] == '_') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Bienvenidos!");

        while (true) {
            inicializarMatriz();
            char jugador = 'X';  // usuario siempre empezar con 'X'
            boolean juegoTerminado = false;

            while (!juegoTerminado) {
                mostrarMatriz();
                if (jugador == 'X') {
                    jugadorUsuario();
                    juegoTerminado = verificarGanador('X');
                    jugador = 'O'; // turno de la computadora
                } else {
                    jugadorComputadora();
                    juegoTerminado = verificarGanador('O');
                    jugador = 'X'; // turno del usuario
                }

                if (esEmpate()) {
                    mostrarMatriz();
                    System.out.println("Empate!");
                    juegoTerminado = true;
                }
            }

            System.out.print("quieres jugar otra vez? (s/n): ");
            char respuesta = scanner.next().charAt(0);
            if (Character.toLowerCase(respuesta) != 's') {
                System.out.println("gracias por jugar!");
                break;
            }
        }
    }
}
