import React from 'react'
import {Link} from "react-router-dom";

const header = () => {
    return (
        <header>
            <nav className="navbar navbar-expand-sm navbar-dark navbar-fixed bg-dark">
                <div className="container mx-5">
                    <a className="navbar-brand" href="/movies">MoviesApp</a>
                </div>
                <div className="collapse navbar-collapse" id="navbarCollapse">
                    <ul className="navbar-nav mr-auto">
                        <li className="nav-item active">
                            <Link className="nav-link" to={"/movies"}>Movies</Link>
                        </li>
                        <li className="nav-item active">
                            <Link className="nav-link" to={"/reviews"}>Reviews</Link>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>

    )
}

export default header;