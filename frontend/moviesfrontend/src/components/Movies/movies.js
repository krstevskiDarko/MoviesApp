import React from 'react';
import { Link } from "react-router-dom";
import ReactPaginate from "react-paginate";


const Movies = (props) => {

    const [pagination, updatePagination] = React.useState({
        page: 0,
        size: 3
    })

    const pageCount = props.movies.length === 0 ? 1 : Math.ceil(props.movies.length / pagination.size);
    const offset = pagination.size * pagination.page;
    const nextPageOffset = offset + pagination.size;

    const handlePageChange = ({selected}) => {
        updatePagination({
            ...pagination,
            page: selected
        })
    }

    const [formData, updateFormData] = React.useState({
        title: "",
        genre: "",
        genres: "",
        year: "",
        yearFrom: "",
        yearTo: ""
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) =>{
        e.preventDefault();

        const title = formData.title;
        const genre = formData.genre;
        const genres = formData.genres;
        const year = formData.year;
        const yearFrom = formData.yearFrom;
        const yearTo = formData.yearTo;

        if (!title && !genre && !genres && !year && !yearFrom && !yearTo) {
            props.loadAllMovies();
        } else {
            props.onSearch(title, genre, genres, year, yearFrom, yearTo);
        }
    }

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
            <div className={"container mt-5"}>
                <table className={"table table-striped"}>
                    <thead>
                    <tr>
                        <th scope="col">Title</th>
                        <th scope="col">Description</th>
                        <th scope="col">Genre</th>
                        <th scope="col">Rating</th>
                        <th scope="col">Year</th>
                    </tr>
                    </thead>
                    <tbody>
                    {props.movies.slice(offset, nextPageOffset).map((term)=>{
                        return(
                            <tr key={term.id}>
                                <td>{term.title}</td>
                                <td>{term.description}</td>
                                <td>{term.genre}</td>
                                <td>{term.averageRating}</td>
                                <td>{term.year}</td>
                                <td>
                                    <Link
                                        to={`/movies/${term.id}`}
                                        onClick={() =>
                                            props.selectMovie(term.id)}>
                                        Get more details!
                                    </Link>
                                </td>
                                <td>
                                    <Link to={`/movies/${term.id}/review`} onClick={() => props.selectMovie(term.id)}> Add your review! </Link>
                                </td>
                                <td>
                                    <Link to={`/movies/${term.id}/rate`} onClick={() => props.selectMovie(term.id)}> Add your rating! </Link>
                                </td>
                            </tr>
                        );
                    })}
                    </tbody>
                </table>
            </div>
            <ReactPaginate previousLabel={"Back"}
                           nextLabel={"Next"}
                           breakLabel={<a href={"/#"}>...</a>}
                           breakClassName={"break-me"}
                           pageClassName={"ml-1"}
                           pageCount={pageCount}
                           marginPagesDisplayed={2}
                           pageRangeDisplayed={6}
                           onPageChange={handlePageChange}
                           containerClassName={"pagination m-4 justify-content-center"}
                           activeClassName={"active"}
            />
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
