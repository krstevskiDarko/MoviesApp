import React from "react";
import {Link} from "react-router-dom";

const MovieDetails = (props) => {

    return (
        <div>
            <div>
                <table>
                    <thead>
                    <tr>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Genre</th>
                        <th>Year</th>
                        <th>Rating</th>
                        <th>RatingId</th>
                        <th>ReviewId</th>
                    </tr>
                    </thead>
                    <tbody>
                            <tr>
                                <td>{props.movie.title}</td>
                                <td>{props.movie.description}</td>
                                <td>{props.movie.genre}</td>
                                <td>{props.movie.year}</td>
                                <td>{props.movie.averageRating}</td>
                                <td>{props.movie.ratingIds}</td>
                                <td>{props.movie.reviewIds}</td>
                            </tr>
                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default MovieDetails;