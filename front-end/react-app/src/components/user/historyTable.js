import React, { Component } from "react";
import "../../assets/styles/user-styles.css";
import "bootstrap/dist/css/bootstrap.min.css";
import HistoryTableRow from "./historyTableRow";

class HistoryTable extends Component {
  render() {
    return (
        <div class="table-responsive">
            <table class="table custom-table">
                <thead>
                    <tr>
                        <th scope="col">Image</th>
                        <th scope="col">Name</th>
                        <th scope="col">Categories</th>
                        <th scope="col">Price</th>
                        <th scope="col">Provider ID</th>
                        <th scope="col">Rating</th>
                        <th scope="col">In Stock</th>
                        <th scope="col">Quantity</th>
                    </tr>
                </thead>

                <tbody>
                    {
                        this.props.historyTableData.map(
                            (item) => (
                                <HistoryTableRow item={item} />
                            )
                        )
                    }
                </tbody>
            </table>
        </div>
    );
  }
}

export default HistoryTable;
