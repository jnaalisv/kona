const path = require("path");
const webpack = require("webpack");

module.exports = {
    entry: "./src/index.tsx",
    mode: "development",
    devtool: "source-map",
    module: {
        rules: [
            {
                test: /\.ts(x?)$/,
                exclude: /(node_modules)/,
                use: ["ts-loader"]
            },
            {
                test: /\.css$/,
                use: ["style-loader", "css-loader"]
            },
            {
                enforce: "pre",
                test: /\.js$/,
                loader: "source-map-loader"
            }
        ]
    },
    resolve: { extensions: ["*", ".ts", ".tsx"] },
    output: {
        path: path.resolve(__dirname, "dist/"),
        publicPath: "/dist/",
        filename: "bundle.js"
    },
    devServer: {
        contentBase: path.join(__dirname, "public/"),
        port: 3000,
        publicPath: "http://localhost:3000/dist/"//,
        //hotOnly: true
    },
//    plugins: [new webpack.HotModuleReplacementPlugin()],

    // When importing a module whose path matches one of the following, just
    // assume a corresponding global variable exists and use that instead.
    // This is important because it allows us to avoid bundling all of our
    // dependencies, which allows browsers to cache those libraries between builds.
    externals: {
        "react": "React",
        "react-dom": "ReactDOM"
    }
};
