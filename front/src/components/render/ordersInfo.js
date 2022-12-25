import {useEffect, useState} from 'react';
import {getOrderInfo, getOrdersInfo} from '../../api/apiService';
import {Button, Card, Container, Dropdown, Form, Modal} from "react-bootstrap";
import BootstrapTable from 'react-bootstrap-table-next';
import '../../styles/commonStyles.css';
import {useCookies} from 'react-cookie';

export default function GetOrdersInfo(props) {
    const [data, setData] = useState([]);
    const [products, setProducts] = useState([]);
    const [cookies, setCookie] = useCookies(['user']);
    const [show, setShow] = useState(false);
    const [orderInfo, setOrderInfo] = useState();
    const [errorMessage, setErrorMessage] = useState('');
    const columns = [
        { dataField: 'id', text: 'Id' },
        {
            dataField: "name",
            text: "Actor name",
            formatter: (cell, row) => {
                return <div>{row['firstname'] + " " + row['lastname']}</div>;
            }
        },
        {
            dataField: "messagesToProcess",
            text: "Message in the queue",
            formatter: (cell, row) => {
                return <div>{row['address1'] + " " + row['address2']}</div>;
            }
        },
        { dataField: 'messagesProcessed', text: 'Messages processed'},
        { dataField: 'status', text: 'Status'},
    ];

    const rowEvents = {
        onClick: (e, row, rowIndex) => {
            handleShow(row.id_order);
        }
    };

    const handleClose = () => setShow(false);
    const handleShow = async (value) => {
        let orderInfo = await getOrderInfo(value);
        orderInfo = orderInfo.message;
        if (orderInfo.length > 0) {
            orderInfo = orderInfo[0];
            orderInfo =
                <div>
                    <Card>
                        <Card.Header as="h5">Pedido: {orderInfo.id_order}</Card.Header>
                        <Card.Body>
                            <Card.Title>Cliente</Card.Title>
                            <Card.Text>
                                {orderInfo.firstname} {orderInfo.lastname}
                            </Card.Text>
                            <Card.Title>Dirección</Card.Title>
                            <Card.Text>
                                {orderInfo.address1} {orderInfo.address2}
                            </Card.Text>
                            <Card.Title>País</Card.Title>
                            <Card.Text>
                                {orderInfo.country}
                            </Card.Text>
                            <Card.Title>Productos</Card.Title>
                            <Card.Text>
                                {orderInfo.product_name}
                            </Card.Text>
                            <Card.Title>Estado del pedido</Card.Title>
                            <Card.Text>
                                <div onChange={(e) => ""}>
                                    <Form.Select aria-label="Default select example">
                                        <option value={orderInfo.current_state_name}>{orderInfo.current_state_name}</option>
                                        {""}
                                    </Form.Select>
                                </div>
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </div>
            setOrderInfo(orderInfo);
            setShow(true);
        }
    }

    async function updateDataFetch() {
        let dataInfo = await getOrdersInfo();
        dataInfo = dataInfo.message;
        return dataInfo[0].numberOrders;
    }

    function useFetch() {
        async function getData() {
            let dataInfo = await getOrdersInfo();
            dataInfo = dataInfo.message;

            const listCategory = dataInfo.filter((item => {
                if (item.current_state === 2 || item.current_state === 3) {
                    return (
                        item
                    )
                }
            }));

            setData(dataInfo);
            setProducts(listCategory);
        }

        useEffect(() => {
            getData();
        }, [])

        useEffect(() => {
            async function updateData() {
                const orders = await updateDataFetch();
                if(orders !== parseInt(cookies.orders)){
                    getData();
                    setCookie("orders", orders);
                }

            }
            const interval = setInterval(() => {
                updateData()
            }, 1000);

            return () => clearInterval(interval);
        }, []);
        return (
            <>
                <BootstrapTable keyField='id' data={products} columns={columns} rowEvents={rowEvents} />
            </>
        );
    }

    return (
        <div>
            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Información del producto</Modal.Title>
                </Modal.Header>
                <Modal.Body>{orderInfo}</Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Cerrar
                    </Button>
                    <Button variant="primary" onClick={""}>
                        Guardar cambios
                    </Button>
                    {errorMessage.length > 0 && <p className="error">{errorMessage}</p>}
                </Modal.Footer>
            </Modal>
            {useFetch()}
        </div>
    )
}