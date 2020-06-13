import React from "react";
import { Link } from "react-router-dom";
import './Navbar.css'

function Navbar() {
    return (
        <nav className="navbar navbar-expand-lg navbar-dark">
            <Link to="/">
                <a className="navbar-brand text-white text-uppercase" href="#">rooms booking</a>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"/>
                </button>
            </Link>
            <div className="collapse navbar-collapse" id="navbarNav">
                <ul className="navbar-nav">
                    <Link to="/guest">
                        <li className="nav-item nav-link text-white text-uppercase">guest</li>
                    </Link>
                    <Link to="/manager">
                        <li className="nav-item nav-link text-white text-uppercase">manager</li>
                    </Link>
                </ul>
            </div>

        </nav>
    )
}

export default Navbar;