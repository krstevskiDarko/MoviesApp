import React from 'react'

const Reviews = (props) => {
    return(
        <div>
            <div>
                <table className={"table"}>
                    <thead>
                    <tr>
                        <th scope={"col"}>Movie ID</th>
                        <th scope={"col"}>Movie Name</th>
                        <th scope={"col"}>Movie Description</th>
                        <th scope={"col"}>Average Rating</th>
                        <th scope={"col"}>Review</th>
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