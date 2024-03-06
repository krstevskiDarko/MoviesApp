import React from 'react';
import {Link} from "react-router-dom";

const Movies = (props) => {
    return (
      <div>
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