import React, {useState} from 'react';
import { Link } from "react-router-dom";
import MoviesService from "../../repository/moviesRepository";


const Movies = (props) => {
    console.log("Movies props:", props.movies);

    const [formData, updateFormData] = useState({
        title: "",
        genre: "",
        genres: "",
        year: "",
        yearFrom: "",
        yearTo: ""
    });

    const [currentPage, setCurrentPage] = useState(0);

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        });
    };

    const onFormSubmit = async (e) => {
        e.preventDefault();

        const { title, genre, genres, year, yearFrom, yearTo } = formData;

        try {
            const response = await MoviesService.fetchMovies(title, genre, genres, year, yearFrom, yearTo, 0);
                props.setMovies(response.data.content);

        } catch (error) {
            console.error(error);
        }
    };

    const paginate = async (pageNumber) => {
        setCurrentPage(pageNumber);

        const { title, genre, genres, year, yearFrom, yearTo } = formData;

        try {
            const response = await MoviesService.fetchMovies(title, genre, genres, year, yearFrom, yearTo, pageNumber - 1);
            props.setMovies(response.data.content);

        } catch (error) {
            console.error(error);
        }
    };
        return (
        <div className={"container"}>
            <form onSubmit={onFormSubmit}>
                <div className="form-group m-1">
                    <input type="text"
                           className="form-control m-1"
                           id="title"
                           name="title"
                           placeholder="Search by Title"
                           onChange={handleChange}
                    />
                    <input type="text"
                           className="form-control m-1"
                           id="genre"
                           name="genre"
                           placeholder="Search by Genre"
                           onChange={handleChange}
                    />
                    <input type="text"
                           className="form-control m-1"
                           id="genres"
                           name="genres"
                           placeholder="Search by Genres (separate them with ,)"
                           onChange={handleChange}
                    />
                    <input type="number"
                           className="form-control m-1"
                           id="year"
                           name="year"
                           placeholder="Search by Year"
                           onChange={handleChange}
                    />
                    <input type="number"
                           className="form-control m-1"
                           id="yearFrom"
                           name="yearFrom"
                           placeholder="Search by YearFrom"
                           onChange={handleChange}
                    />
                    <input type="number"
                           className="form-control m-1"
                           id="yearTo"
                           name="yearTo"
                           placeholder="Search by YearTo"
                           onChange={handleChange}
                    />
                    <div className={"col-auto"}>
                        <button id="submit" type="submit" className="btn btn-primary">Search</button>
                    </div>
                </div>
            </form>
            <div className="container pt-3">
                <div className="row">
                    {props.movies.map((term)=>{
                        return(
                            <div className="col-lg-4 my-2">
                                <div className="card border border-secondary shadow">
                                    <div className="card-body">
                                        <span className="card-subtitle text-secondary mb-2 d-block">{term.genre}</span>
                                        <span className="card-subtitle text-secondary mb-2 d-block">{term.year}</span>
                                        <h3 className="card-title">{term.title}</h3>
                                        <hr />
                                        <p className="card-text">{term.description}</p>
                                        <hr />
                                        <p className="card-text fw-bold">Rating: {term.averageRating}</p>
                                        <div className="btn-group mx-auto" role="group">
                                            <Link
                                                to={`/movies/${term.id}`}
                                                className="btn btn-outline-primary"
                                                onClick={() => props.selectMovie(term.id)}>
                                                Get more details
                                            </Link>
                                            <Link
                                                to={`/movies/${term.id}/review`}
                                                className="btn btn-outline-success"
                                                onClick={() => props.selectMovie(term.id)}>
                                                Add your review
                                            </Link>
                                            <Link
                                                to={`/movies/${term.id}/rate`}
                                                className="btn btn-outline-warning"
                                                onClick={() => props.selectMovie(term.id)}>
                                                Add your rating
                                            </Link>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        );
                    })}
                </div>
                <ul className="pagination justify-content-center">
                    {Array.from({ length: Math.ceil(props.movies.length / 10) }).map((_, index) => (
                        <li className={`page-item ${index + 1 === currentPage && 'active'}`} key={index}>
                            <button className="page-link" onClick={() => paginate(index + 1)}>
                                {index + 1}
                            </button>
                        </li>
                    ))}
                </ul>
            </div>
            <div className="col mb-3">
                <div className="row">
                    <div className="col-sm-12 col-md-12">
                        <Link className={"btn btn-block btn-success"} to={"/movies/add"}>Add new movie!</Link>
                    </div>
                </div>
            </div>

        </div>
    );
}

export default Movies;
