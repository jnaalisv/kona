"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const react_1 = __importDefault(require("react"));
require("./App.css");
class App extends react_1.default.Component {
    render() {
        return (react_1.default.createElement("div", { className: "App" },
            react_1.default.createElement("h1", null,
                " ",
                this.props.greeting,
                "! ")));
    }
}
exports.App = App;
