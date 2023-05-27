import React, { Component } from "react";
import "../../assets/styles/product-styles.css";
import "bootstrap/dist/css/bootstrap.min.css";

class PostNewComment extends Component {
  constructor(props) {
    super(props);
    this.state = {
      commentText: "",
    };
    this.handleCommentTextChange = this.handleCommentTextChange.bind(this);
  }

  handleCommentTextChange(e) {
    this.setState({ commentText: e.target.value }, () => {
      // console.log(this.state.searchField);
    });
  }

  render() {
    return (
      <div className="row mb-3">
        <form
          action=""
          onSubmit={(event) =>
            this.props.onPostComment(event, this.state.commentText)
          }
        >
          <label for="opinion" className="form-label text-brown h4">
            Submit your opinion
          </label>
          <div className="d-flex">
            <div className="col-lg-11">
              <textarea
                className="form-control comment-text-input"
                id="opinion"
                rows="3"
                onChange={this.handleCommentTextChange}
                required
              ></textarea>
            </div>
            <div className="col-lg-1">
              <button className="= btn comment-submit-button" type="submit">
                Post
              </button>
            </div>
          </div>
        </form>
      </div>
    );
  }
}

export default PostNewComment;
