import React from "react";
import "./css/style.css";
import MainService from "./service/MainService";
import {Button} from "@material-ui/core";

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            history: [],
            current: "",
            symbol: true
        }
    }

    getData = () => {
        MainService.getData().then(response => {
            let arr = response.data;
            arr.forEach(el => {
                console.log(el);
                this.calculate(el);
            });
        })
    }

    calculate = (str) => {
        let historyField = this.state.history;

        let res = str.split(/[+\-*/=]/).filter((x) => {
            return x !== "";
        });

        let exceptLastChar = isNaN(str.charAt(str.length - 1))
            ? str.substring(0, str.length - 1)
            : str;
        let first = parseInt(res.at(0));
        let second = parseInt(res.at(1));

        if (exceptLastChar.includes("+")) {
            res = first + second;
        } else if (exceptLastChar.includes("-")) {
            res = first - second;
        } else if (exceptLastChar.includes("*")) {
            res = first * second;
        } else if (exceptLastChar.includes("/")) {
            if (second === 0) {
                res = "Error division by zero";
            } else {
                res = first / second;
            }
        }
        let a = Math.round(res * 100) / 100;

        res = isNaN(a) ? res : a;

        historyField.push(exceptLastChar + "=" + res);

        this.setState({history: historyField});

        return res;
    }

    click = (e) => {
        let symbol = this.state.symbol;
        let currentField = this.state.current;
        let historyField = this.state.history;

        let str = currentField;

        let text = e.currentTarget.textContent;
        if (text === "C") {
            this.setState({current: ""});
            return;
        }

        if (isSymbol(text)) {
            if (symbol) {
                str = replaceCharacter(str, str.length - 1, text);
            } else {
                str += text;
                symbol = true;
            }

            let res = str.split(/[+\-*/=]/).filter((x) => {
                return x !== ""
            });
            if (res.length === 2) {
                res = this.calculate(str);
                let lastChar = str.at(str.length - 1);
                symbol = lastChar !== "=";
                str = (typeof res === "string" ? 0 : res) + (lastChar !== "=" ? lastChar : "");
            }
        } else {
            str += text;
            symbol = false;
        }

        if (isSymbol(str)) {
            str = "";
        }
        if (str.at(str.length - 1) === "=") {
            str = str.replace("=", "");
            symbol = false;
        }

        currentField = str;

        this.setState({
            symbol: symbol,
            current: currentField
        });

        function isSymbol(symbol) {
            return symbol === "-" || symbol === "+" ||
                symbol === "/" || symbol === "*" || symbol === "=";
        }

        function replaceCharacter(string, index, replacement) {
            return (
                string.substring(0, index) +
                replacement +
                str.substring(index + replacement.length)
            );
        }
    }

    render() {
        return (
            <div className="App">
                <div style={{
                    margin: "150px auto 0",
                    textAlign: "center",
                    color: "white"
                }}>
                    <Button variant="contained" color="primary" onClick={this.getData}
                    >Отримати та вирішити приклади</Button>
                </div>
                <table>
                    <tbody>
                    <tr>
                        <td className="history" id="history" colSpan="4">{this.state.history.map(
                            problem => (<span> {problem}
                                <div></div></span>)
                        )}</td>
                    </tr>
                    <tr>
                        <td className="history" id="current" colSpan="4">{this.state.current}</td>
                    </tr>
                    <tr>
                        <td onClick={this.click}>C</td>
                        <td onClick={this.click}>*</td>
                        <td onClick={this.click}>/</td>
                        <td onClick={this.click}>-</td>
                    </tr>
                    <tr>
                        <td onClick={this.click}>7</td>
                        <td onClick={this.click}>8</td>
                        <td onClick={this.click}>9</td>
                        <td onClick={this.click}>0</td>
                    </tr>
                    <tr>
                        <td onClick={this.click}>4</td>
                        <td onClick={this.click}>5</td>
                        <td onClick={this.click}>6</td>
                        <td onClick={this.click}>+</td>
                    </tr>
                    <tr>
                        <td onClick={this.click}>1</td>
                        <td onClick={this.click}>2</td>
                        <td onClick={this.click}>3</td>
                        <td className="equals" onClick={this.click}>=</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        );
    }
}

export default App;
