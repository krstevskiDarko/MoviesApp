import React, { useState } from 'react';
import { Link } from "react-router-dom";

const Movies = (props) => {

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
        <div>
            <form onSubmit={onFormSubmit}>
                <div className="form-group">
                    <input type="text"
                           className="form-control"
                           id="title"
                           name="title"
                           placeholder="Search by Title"
                           onChange={handleChange}
                    />
                    <input type="text"
                           className="form-control"
                           id="genre"
                           name="genre"
                           placeholder="Search by Genre"
                           onChange={handleChange}
                    />
                    <input type="text"
                           className="form-control"
                           id="genres"
                           name="genres"
                           placeholder="Search by Genres"
                           onChange={handleChange}
                    />
                    <input type="number"
                           className="form-control"
                           id="year"
                           name="year"
                           placeholder="Search by Year"
                           onChange={handleChange}
                    />
                    <input type="number"
                           className="form-control"
                           id="yearFrom"
                           name="yearFrom"
                           placeholder="Search by YearFrom"
                           onChange={handleChange}
                    />
                    <input type="number"
                           className="form-control"
                           id="yearTo"
                           name="yearTo"
                           placeholder="Seach by YearTo"
                           onChange={handleChange}
                    />
                    <button id="submit" type="submit" className="btn btn-primary">Search</button>
                </div>
            </form>
            <div>
              <table>
                  <thead>
                    <tr>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Genre</th>
                        <th>Rating</th>
                        <th>Year</th>
                    </tr>
                  </thead>
                  <tbody>
                      {props.movies.map((term)=>{
                          return(
                            <tr>
                                <td >{term.title}</td>
                                <td>{term.description}</td>
                                <td>{term.genre}</td>
                                <td>{term.averageRating}</td>
                                <td>{term.year}</td>
                                <td>
                                    <Link
                                        to={`/movies/${term.id}`}
                                        onClick={() =>
                                            props.selectMovie(term.id)
                                    }
                                    >
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
          <div>
              <Link to={"/movies/add"}>Add Movie</Link>
          </div>
      </div>
    );
}

export default Movies;