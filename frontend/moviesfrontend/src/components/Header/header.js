import React from 'react'
import {Link} from "react-router-dom";

const header = () => {
    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <div className="container-fluid">
                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav">
                        <li className="nav-item">
                            <Link className="navbar-brand" to={"/movies"}>Movies</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="navbar-brand" to={"/reviews"}>Reviews</Link>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    )
}

export default header;