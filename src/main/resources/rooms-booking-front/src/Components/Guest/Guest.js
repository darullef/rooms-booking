import React, {Component} from "react";
import './Guest.css'

class Guest extends Component {
    state = {
        rooms: []
    }

    componentDidMount() {
        fetch('http://localhost:8080/api/room')
            .then(response => response.json())
            .then(json => {
                console.log(json)
                this.setState({
                    rooms: json
                })
            })

    }

    render() {
        let rooms = this.state.rooms.map((room) => {
            return (
                <tr key={room.id}>
                    <td>{room.number}</td>
                    <td>{room.capacity}</td>
                    <td>{room.hasProjector.toString()}</td>
                    <td>
                        <button className="btn btn-primary">Book</button>
                    </td>
                </tr>
            )
        })

        return (
            <div className="container">
                <div className="card mt-2">
                    <div className="card-body">
                        <h5 className="card-title">Find room for you!</h5>
                        <form action="">
                            <div className="row d-flex">
                                <div className="form-group col-2">
                                    <label htmlFor="capacity_min">Capacity</label>
                                    <input type="number" className="form-control" id="capacity_min"/>
                                    <small id="capacity_min_help" className="form-text text-muted">Minimum
                                        capacity</small>
                                </div>
                                <div className="form-group col-2">
                                    <label htmlFor="capacity_max">&nbsp;</label>
                                    <input type="number" className="form-control" id="capacity_max"/>
                                    <small id="capacity_max_help" className="form-text text-muted">Maximum
                                        capacity</small>
                                </div>
                                <div className="form-group col-3">
                                    <label htmlFor="date_start">Date</label>
                                    <input type="date" className="form-control" id="date_start"/>
                                    <small id="date_start_help" className="form-text text-muted">Start date</small>
                                </div>
                                <div className="form-group col-3">
                                    <label htmlFor="date_end">&nbsp;</label>
                                    <input type="date" className="form-control" id="date_end"/>
                                    <small id="date_end_help" className="form-text text-muted">End date</small>
                                </div>
                                <div className="form-group form-check col-2">
                                    <input type="checkbox" className="form-check-input" id="has_projector"/>
                                    <label className="form-check-label" htmlFor="has_projector">Projector</label>
                                </div>
                            </div>
                            <div className="row col justify-content-end">
                                <button type="submit" className="btn btn-primary">Filter</button>
                            </div>
                        </form>
                        <table className="table mt-3">
                            <thead className="text-center">
                            <tr>
                                <th>Number</th>
                                <th>Capacity</th>
                                <th>Projector</th>
                                <th>&nbsp;</th>
                            </tr>
                            </thead>
                            <tbody className="text-center">
                            {rooms}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        );
    }
}

export default Guest;