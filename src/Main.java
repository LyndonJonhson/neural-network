public class Main {

    public static void main(String[] args) {

        NeuralNetwork nn = new NeuralNetwork(7, 20, 4);
        Double[][] input = ReadFile.readInput("X_In.csv");
        Integer[][] output = ReadFile.readOutput("Y_Out.csv");

        boolean train = true;

        while(train) {
            for (int i = 0; i < input.length; i++) {
                nn.train(input[i], output[i]);
            }

            nn.accuracy(input, output);

            System.out.println(nn.acc);

            if (nn.acc > 95d) {
                System.out.println("Sucesso");

                System.out.println("Pesos IH:");
                for (int i = 0; i < nn.ih_weights.length; i++) {
                    for (int j = 0; j < nn.ih_weights[0].length; j++) {
                        System.out.print(nn.ih_weights[i][j] + ", ");
                    }
                    System.out.println();
                }
                System.out.println("Pesos HO:");
                for (int i = 0; i < nn.ho_weights.length; i++) {
                    for (int j = 0; j < nn.ho_weights[0].length; j++) {
                        System.out.print(nn.ho_weights[i][j] + ", ");
                    }
                    System.out.println();
                }

                System.out.println("Bias IH:");
                for (int i = 0; i < nn.ih_bias.length; i++) {
                    for (int j = 0; j < nn.ih_bias[0].length; j++) {
                        System.out.print(nn.ih_bias[i][j] + ", ");
                    }
                    System.out.println();
                }
                System.out.println("Bias HO:");
                for (int i = 0; i < nn.ho_bias.length; i++) {
                    for (int j = 0; j < nn.ho_bias[0].length; j++) {
                        System.out.print(nn.ho_bias[i][j] + ", ");
                    }
                    System.out.println();
                }

                break;
            }
        }
    }
}
