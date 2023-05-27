import React, { Component } from "react";
import "../../assets/styles/product-styles.css";
import "bootstrap/dist/css/bootstrap.min.css";
import Comment from "./comment";
import PostNewComment from "./postNewComment";

class Comments extends Component {
  render() {
    return (
      <div className="bg-light">
        <div className="container product-info-container">
          <div className="container-fluid justify-content-around">
            <div className="row comments-section bg-white m-5 p-4 shadow-sm">
              <div className=" bg-white">
                <div className="row pb-3">
                  <p className="h4 text-brown">
                    Comments{" "}
                    <span className="text-gray">
                      ({this.props.comments.length})
                    </span>
                  </p>
                </div>
                {this.props.comments.map((item) => (
                  <Comment
                    onVoteComment={this.props.onVoteComment}
                    comment={item}
                  />
                ))}
                <PostNewComment onPostComment={this.props.onPostComment} />
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Comments;
