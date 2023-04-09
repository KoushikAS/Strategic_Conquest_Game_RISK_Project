import React from "react";
import { Card, Form, Button, Container } from "react-bootstrap";

const LoginView = () => {
  const cardStyles = {
    width: "30rem",
    height: "25rem",
    borderRadius: "2rem",
    boxShadow: "0 0 10px rgba(0, 0, 0, 0.6)",
  };
  const titleStyles = {
    fontSize: "2rem",
    color: "#77A6F7",
  };
  return (
    <Container className="vh-100 d-flex align-items-center justify-content-center">
      <Card style={cardStyles}>
        <Card.Body>
          <Card.Title className="text-center" style={titleStyles}>
            Sign In
          </Card.Title>
          <Form>
            <Form.Group controlId="formEmail">
              <Form.Label>Email</Form.Label>
              <Form.Control
                type="email"
                placeholder="Email"
                style={{ backgroundColor: "#e9f0fd" }}
              />
            </Form.Group>

            <Form.Group controlId="formPassword">
              <Form.Label>Password</Form.Label>
              <Form.Control
                type="password"
                placeholder="Password"
                style={{ backgroundColor: "#e9f0fd" }}
              />
            </Form.Group>

            <Button variant="primary" type="submit" block>
              Sign In
            </Button>
          </Form>
          <p className="mt-3 mb-0 text-center">Welcome back!</p>
        </Card.Body>
      </Card>
    </Container>
  );
};

export default LoginView;
