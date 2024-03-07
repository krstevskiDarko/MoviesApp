import React from 'react'
import ReactPaginate from "react-paginate";

const Reviews = (props) => {

    const [pagination, updatePagination] = React.useState({
        page: 0,
        size: 3
    })

    const pageCount = props.reviews.length === 0 ? 1 : Math.ceil(props.reviews.length / pagination.size);
    const offset = pagination.size * pagination.page;
    const nextPageOffset = offset + pagination.size;

    const handlePageChange = ({selected}) => {
        updatePagination({
            ...pagination,
            page: selected
        })
    }

    return(
        <div className={"container"}>
            <div>
                <table className="table table-striped border rounded-lg border-secondary shadow">
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
                    {props.reviews.slice(offset, nextPageOffset).map((term)=>{
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
            </div>
        </div>
    );
}

export default Reviews;