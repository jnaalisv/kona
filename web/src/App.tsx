import React from "react";
import "./App.css";

export interface AppProps { greeting: string; }
export interface AppState { }

export class App extends React.Component<AppProps, {}> {
    render(){
        return(
            <div className="App">
                <h1> {this.props.greeting}! </h1>
            </div>
        );
    }
}
