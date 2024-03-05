import React from 'react'

const Reviews = (props) => {
    return(
        <div>
            <div>
                <table>
                    <thead>
                    <tr>
                        <th>Movie ID</th>
                        <th>Movie Name</th>
                        <th>Movie Description</th>
                        <th>Average Rating</th>
                        <th>Review</th>
                    </tr>
                    </thead>
                    <tbody>
                    {props.reviews.map((term)=>{
                        return(
                            <tr>
                                <td>{term.movieId}</td>
                                <td>{term.movieTitle}</td>
                                <td>{term.movieDescription}</td>
                                <td>{term.movieRating}</td>
                                <td>{term.review}</td>
                            </tr>
                        );
                    })}
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default Reviews;