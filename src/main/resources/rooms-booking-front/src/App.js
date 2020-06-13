import React from 'react';
import {Switch, Route} from "react-router-dom";
import './App.css';
import Navbar from "./components/Navbar/Navbar";
import Footer from "./components/Footer/Footer";
import Guest from "./components/Guest/Guest";
import Manager from "./components/Manager/Manager";

import 'bootstrap/dist/css/bootstrap.min.css';
import '@fortawesome/fontawesome-free/css/all.css';

function App() {
    return (
        <div className="App">
            <Navbar/>
            <Switch>
                <Route exact path="/" component={Home}/>
                <Route path="/guest" component={Guest}/>
                <Route path="/manager" component={Manager}/>
            </Switch>
            <Footer/>
        </div>
    );
}

const Home = () => (
    <div className="container">
        <div className="card mt-2">
            <div className="card-body">
                <h5 className="card-title">Rooms booking</h5>
                <span>Web application created with Spring Boot and React</span>
            </div>
        </div>
    </div>
);

export default App;
