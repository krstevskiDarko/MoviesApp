import React from "react";
import {Link} from "react-router-dom";

const MovieDetails = (props) => {

    return (
        <div>
            <div>
                <table className={"table"}>
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
                                <td scope="col">{props.movie.title}</td>
                                <td scope="col">{props.movie.description}</td>
                                <td scope="col">{props.movie.genre}</td>
                                <td scope="col">{props.movie.year}</td>
                                <td scope="col">{props.movie.averageRating}</td>
                                <td scope="col">{props.movie.ratings && props.movie.ratings.join('; ')}</td>
                                <td scope="col">{props.movie.reviews && props.movie.reviews.join('; ')}</td>
                            </tr>
                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default MovieDetails;