import java.util.Random;

public class NeuralNetwork {
    Integer input_nodes;
    Integer hidden_nodes;
    Integer output_nodes;
    Double learning_rate;

    Double[][] ih_bias;
    Double[][] ho_bias;
    Double[][] ih_weights;
    Double[][] ho_weights;
    Double acc = 0d;

    public NeuralNetwork(Integer input_nodes, Integer hidden_nodes, Integer output_nodes) {
        this.input_nodes = input_nodes;
        this.output_nodes = output_nodes;
        this.hidden_nodes = hidden_nodes;

        Random random = new Random();

        Double[][] ih_bias = new Double[hidden_nodes][1];
        Double[][] ho_bias = new Double[output_nodes][1];
        Double[][] ih_weights = new Double[hidden_nodes][input_nodes];
        Double[][] ho_weights = new Double[output_nodes][hidden_nodes];

        for (int i = 0; i < hidden_nodes; i++) {
                ih_bias[i][0] = random.nextDouble();
        }
        for (int i = 0; i < output_nodes; i++) {
                ho_bias[i][0] = random.nextDouble();
        }
        for (int i = 0; i < hidden_nodes; i++) {
            for (int j = 0; j < input_nodes; j++) {
                ih_weights[i][j] = random.nextDouble();
            }
        }
        for (int i = 0; i < output_nodes; i++) {
            for (int j = 0; j < hidden_nodes; j++) {
                ho_weights[i][j] = random.nextDouble();
            }
        }

        this.ih_bias = ih_bias;
        this.ho_bias = ho_bias;
        this.ih_weights = ih_weights;
        this.ho_weights = ho_weights;

        this.learning_rate = 0.1d;
    }

    public Double[][] sigmoid(Double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = 1 / (1 + Math.exp(-matrix[i][j]));
            }
        }
        return matrix;
    }

    public Double[][] d_sigmoid(Double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = matrix[i][j] * (1 - matrix[i][j]);
            }
        }
        return matrix;
    }

    public void train(Double[] arr, Integer[] target) {
        // FEEDFORWARD
        // INPUT -> HIDDEN
        Double[][] input = Matrix.arrayToMatrix_Double(arr);
        Double[][] hidden = Matrix.mult(this.ih_weights, input);
        hidden = Matrix.add(hidden, this.ih_bias);
        hidden = sigmoid(hidden);

        // HIDDEN -> OUTPUT
        Double[][] output = Matrix.mult(this.ho_weights, hidden);
        output = Matrix.add(output, this.ho_bias);
        output = sigmoid(output);

        // BACKPROPAGATION
        // OUTPUT -> HIDDEN
        Integer[][] expected = Matrix.arrayToMatrix_Integer(target);
        Double[][] output_error = Matrix.sub(expected, output);
        Double[][] d_output = d_sigmoid(output);
        Double[][] hidden_t = Matrix.transpose(hidden);

        Double[][] gradient = Matrix.hadamard(d_output, output_error);
        gradient = Matrix.scalarMult(gradient, this.learning_rate);

        // Adjust bias Output -> Hidden
        this.ho_bias = Matrix.add(this.ho_bias, gradient);
        // Adjust weights Output -> Hidden
        Double[][] delta_ho_weights = Matrix.mult(gradient, hidden_t);
        this.ho_weights = Matrix.add(this.ho_weights, delta_ho_weights);

        // HIDDEN -> INPUT
        Double[][] ho_weights_t = Matrix.transpose(this.ho_weights);
        Double[][] hidden_error = Matrix.mult(ho_weights_t, output_error);
        Double[][] d_hidden = d_sigmoid(hidden);
        Double[][] input_t = Matrix.transpose(input);

        Double[][] gradient_H = Matrix.hadamard(d_hidden, hidden_error);
        gradient_H = Matrix.scalarMult(gradient_H, this.learning_rate);

        // Adjust bias H -> I
        this.ih_bias = Matrix.add(this.ih_bias, gradient_H);
        // Adjust weights H -> I
        Double[][] delta_ih_weights = Matrix.mult(gradient_H, input_t);
        this.ih_weights = Matrix.add(this.ih_weights, delta_ih_weights);
    }

    public Double[] predict(Double[] arr) {
        // INPUT -> HIDDEN
        Double[][] input = Matrix.arrayToMatrix_Double(arr);
        Double[][] hidden = Matrix.mult(this.ih_weights, input);
        hidden = Matrix.add(hidden, this.ih_bias);
        hidden = sigmoid(hidden);

        // HIDDEN -> OUTPUT
        Double[][] output = Matrix.mult(this.ho_weights, hidden);
        output = Matrix.add(output, this.ho_bias);
        output = sigmoid(output);
        Double[] output_arr = Matrix.matrixToArray(output);

        return output_arr;
    }

    public void accuracy(Double[][] input, Integer[][] output) {
        Double[][] predict = new Double[input.length][];
        Double erro = 0d;

        for (int i = 0; i < input.length; i++) {
            predict[i] = predict(input[i]);
            for (int j = 0; j < output[0].length; j++) {
                erro += Math.abs(predict[i][j] - output[i][j]);
            }
        }
        erro /= (input.length * output[0].length);
        this.acc = (1 - erro) * 100;
    }

    public void setIh_bias(Double[][] ih_bias) {
        this.ih_bias = ih_bias;
    }

    public void setHo_bias(Double[][] ho_bias) {
        this.ho_bias = ho_bias;
    }

    public void setIh_weights(Double[][] ih_weights) {
        this.ih_weights = ih_weights;
    }

    public void setHo_weights(Double[][] ho_weights) {
        this.ho_weights = ho_weights;
    }
}
