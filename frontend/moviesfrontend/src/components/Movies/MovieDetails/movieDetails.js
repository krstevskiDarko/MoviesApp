import React from "react";
import {Link} from "react-router-dom";

const MovieDetails = (props) => {

    return (
        <div>
            <div>
                <table className="table table-striped border rounded-lg border-secondary shadow">
                    <thead>
                    <tr>
                        <th scope="col">Title</th>
                        <th scope="col">Description</th>
                        <th scope="col">Genre</th>
                        <th scope="col">Year</th>
                        <th scope="col">Rating</th>
                        <th scope="col">Ratings</th>
                        <th scope="col">Reviews</th>
                    </tr>
                    </thead>
                    <tbody>
                            <tr>
                                <td>{props.movie.title}</td>
                                <td>{props.movie.description}</td>
                                <td>{props.movie.genre}</td>
                                <td>{props.movie.year}</td>
                                <td>{props.movie.averageRating}</td>
                                <td>{props.movie.ratings && props.movie.ratings.join(' ; ')}</td>
                                <td>{props.movie.reviews && props.movie.reviews.join(' ; ')}</td>
                            </tr>
                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default MovieDetails;