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
                                <td scope={"col"}>{term.movieId}</td>
                                <td scope={"col"}>{term.movieTitle}</td>
                                <td scope={"col"}>{term.movieDescription}</td>
                                <td scope={"col"}>{term.movieRating}</td>
                                <td scope={"col"}>{term.review}</td>
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