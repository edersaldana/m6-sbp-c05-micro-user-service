-- ============================================
-- Migration: V3__INSERT_DATA.sql
-- ============================================

INSERT INTO users (name, email, role, password) VALUES
('Juan Pérez', 'juan.perez@example.com', 'ADMIN', '12345'),
('María García', 'maria.garcia@example.com', 'USER', ''),
('Carlos López', 'carlos.lopez@example.com', 'USER', ''),
('Ana Torres', 'ana.torres@example.com', 'USER', ''),
('Luis Fernández', 'luis.fernandez@example.com', 'USER', ''),
('Roberto Sánchez', 'roberto.sanchez@example.com', 'USER', '');