const path = require('path');

// PLUGINS y MINIFICADORES DE CSS Y SCSS -> Para Reducir el tamaño de las hojas de estilo
// de nuestro proyecto
//const HtmlWebpackPlugin = require('html-webpack-plugin'); // para el template HTML
const MiniCssExtractPlugin = require('mini-css-extract-plugin'); // para reducir los css
const { SourceMapDevToolPlugin } = require('webpack'); // para conocer el Source Map de nuestros archivos

module.exports = {

  entry: './src/index.jsx',
  output: {
    path: path.join(__dirname, '/dist'),
    filename: 'js/index.js',
  },
  context: path.resolve(__dirname),
  devServer: {
    port: 3000,
    inline: true,
  },
  devtool: 'eval-source-map',
  module: {
    rules: [
      // Para los archivos JS y JSX -> Tienen que pasar el Linting
      {
        enforce: 'pre',
        test: /(\.js|\.jsx)$/,
        exclude: /node_modules/,
        use: [
          'eslint-loader',
          'source-map-loader',
        ],
      },
      // Para los archivos JS y JSX -> Babel ES y JSX
      {
        test: /\.(js|jsx)$/,
        exclude: /node_modules/,
        loader: 'babel-loader',
        query: {
          presets: [
            '@babel/env',
            '@babel/react',
          ],
        },
      },
      // Para los archivos de estilos CSS y SCSS
      {
        test: /(\.css|\.scss)$/,
        loader: [
          MiniCssExtractPlugin.loader,
          'css-loader',
          'sass-loader',
        ],
      },
    ],
  },
  plugins: [
    // new HtmlWebpackPlugin(
    //   {
    //     template: '../webapp/index.xhtml',
    //     filename: './index.xhtml',
    //     xhtml: true
    //   },
    // ),
    new MiniCssExtractPlugin(
      {
        filename: 'css/styles.css',
      },
    ),
    new SourceMapDevToolPlugin(
      {
        filename: '[file].map',
      },
    ),
  ],
  resolve: {
    extensions: ['.js', '.jsx'],
  },
};
