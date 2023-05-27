import "../../assets/styles/home-styles.css";
import "bootstrap/dist/css/bootstrap.min.css";
import React, { Component } from "react";

class Filter extends Component {
  render() {
    return (
      <div className="container filter-section">
        <div className="container-fluid">
          <div className="row selection-section p-2 shadow-sm">
            <div className="d-flex justify-content-between">
              <div className="d-flex">
                <div>
                  <h4 className="brown">Available commodities</h4>
                </div>
                <div className="padding-left">
                  <label className="switch">
                    <input
                      type="checkbox"
                      onClick={() => this.props.setAvailabaleFlag()}
                    />
                    <div className="slider round"></div>
                  </label>
                </div>
              </div>
              <div className="d-flex justify-content-between sorting-section">
                <div className="d-flex">
                  <div className="brown sort-by">
                    <h5>sort by:</h5>
                  </div>
                  <div className="padding-left">
                    <button
                      type="button"
                      name="sort-action"
                      value="sort-by-name"
                      className="btn sellected"
                      onClick={() => this.props.onSort("sortByName")}
                    >
                      name
                    </button>
                    <button
                      type="button"
                      name="sort-action"
                      value="sort-by-price"
                      className="btn sellected-2"
                      onClick={() => this.props.onSort("sortByPrice")}
                    >
                      price
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Filter;
