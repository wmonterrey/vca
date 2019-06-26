//Roles y usuario admin
INSERT INTO roles (ROLE) VALUES ('ROLE_ADMIN');
INSERT INTO roles (ROLE) VALUES ('ROLE_SUPER');
INSERT INTO roles (ROLE) VALUES ('ROLE_SPRAY');
INSERT INTO roles (ROLE) VALUES ('ROLE_CENSUS');
INSERT INTO roles (ROLE) VALUES ('ROLE_REVIEW');
INSERT INTO roles (ROLE) VALUES ('ROLE_PASSWORD');
INSERT INTO users (username, credentialsNonExpired, accountNonLocked, changePasswordNextLogin, completeName, created,createdBy, accountNonExpired, email, enabled, lastAccess, lastCredentialChange, modified, modifiedBy, password) VALUES ('admin', b'1', b'1', b'0', 'Administrador del Sistema', '2018-11-08 00:00:00', 'admin', b'1', 'waviles@icsnicaragua.org', b'1', '2018-11-08 00:00:00', '2018-11-08 00:00:00', '2018-11-08 00:00:00', 'admin', '6c36dc262b0e44be5811c2296669fc65643aec9dcaa4a76501e0a9508b633fd01ee59a207f8c6d68');
INSERT INTO userroles (ROLE, username, deviceid, status, pasive, recordDate,recordUser) VALUES ('ROLE_ADMIN', 'admin', 'admin', '2', '0', '2018-11-08 00:00:00', 'admin');
INSERT INTO userroles (ROLE, username, deviceid, status, pasive, recordDate,recordUser) VALUES ('ROLE_PASSWORD', 'admin', 'admin', '2', '0', '2018-11-08 00:00:00', 'admin');
INSERT INTO userroles (ROLE, username, deviceid, status, pasive, recordDate,recordUser) VALUES ('ROLE_SPRAY', 'admin', 'admin', '2', '0', '2018-11-08 00:00:00', 'admin');
INSERT INTO userroles (ROLE, username, deviceid, status, pasive, recordDate,recordUser) VALUES ('ROLE_CENSUS', 'admin', 'admin', '2', '0', '2018-11-08 00:00:00', 'admin');
INSERT INTO userroles (ROLE, username, deviceid, status, pasive, recordDate,recordUser) VALUES ('ROLE_SUPER', 'admin', 'admin', '2', '0', '2018-11-08 00:00:00', 'admin');
INSERT INTO userroles (ROLE, username, deviceid, status, pasive, recordDate,recordUser) VALUES ('ROLE_REVIEW', 'admin', 'admin', '2', '0', '2018-11-08 00:00:00', 'admin');


INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('access', NULL, NULL, 'User access', '0', 0, '0', 'Accesos de usuario');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('actions', NULL, NULL, 'Actions', '0', 0, '0', 'Acciones');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('active', NULL, NULL, 'Active', '0', 0, '0', 'Activo');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('add', NULL, NULL, 'Add', '0', 0, '0', 'Agregar');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('addedBy', NULL, NULL, 'Added by', '0', 0, '0', 'Agregado por');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('admin', NULL, NULL, 'Administration', '0', 0, '0', 'Administraci�n');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('all', NULL, NULL, 'All', '0', 0, '0', 'Todos');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ask.chgpass', NULL, NULL, 'Demand password change', '0', 0, '0', 'Exigir cambio de contrase�a');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('audittrail', NULL, NULL, 'Audit Trail', '0', 0, '0', 'Bitacora de cambios');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('back', NULL, NULL, 'Return/back', '0', 0, '0', 'Regresar');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('backLogin', NULL, NULL, 'Return to login page', '0', 0, '0', 'Regresar a p�gina de ingreso');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('cancel', NULL, NULL, 'Cancel', '0', 0, '0', 'Cancelar');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('category', NULL, NULL, 'Category', '0', 0, '0', 'Categor�a');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('catKey', NULL, NULL, 'Response value', '0', 0, '0', 'Valor de la respuesta');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('catRoot', NULL, NULL, 'Root catalogue', '0', 0, '0', 'Cat�logo Padre');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_SINO', NULL, NULL, 'Catalogue yes/no', '1', 0, '0', 'Catalogo Si No');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_SINO_NO', '0', 'CAT_SINO', 'No', '0', 2, '0', 'No');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_SINO_SI', '1', 'CAT_SINO', 'Yes', '0', 1, '0', 'Si');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('change', NULL, NULL, 'Change', '0', 0, '0', 'Cambiar');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('changepass', NULL, NULL, 'Change password', '0', 0, '0', 'Cambiar contrase�a..');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('choose', NULL, NULL, 'Choose', '0', 0, '0', 'Elegir');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('class org.clintonhealthaccess.vca.users.model.Authority', NULL, NULL, 'Role', '0', 0, '0', 'Rol de Usuario');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('class org.clintonhealthaccess.vca.users.model.UserSistema', NULL, NULL, 'User', '0', 0, '0', 'Usuario');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('confirm', NULL, NULL, 'Confirm', '0', 0, '0', 'Confirmar');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('createdBy', NULL, NULL, 'Created by', '0', 0, '0', 'Creado por');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('credentials.expired', NULL, NULL, 'Your password has expired', '0', 0, '0', 'Su contrase�a ha caducado');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('dateAdded', NULL, NULL, 'Date', '0', 0, '0', 'Fecha');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('dateCreated', NULL, NULL, 'Date created', '0', 0, '0', 'Fecha creacion');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('dateCredentials', NULL, NULL, 'Last change of password', '0', 0, '0', 'Ultimo cambio de contrasena');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('dateModified', NULL, NULL, 'Date of modification', '0', 0, '0', 'Fecha de modificaci�n');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('delete', NULL, NULL, 'Delete', '0', 0, '0', 'Eliminar');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('denied', NULL, NULL, 'Access denied', '0', 0, '0', 'Acceso denegado');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('deniedmessage', NULL, NULL, 'Sorry, the page you\'re looking for isn\'t available with your credentials', '0', 0, '0', 'Lo sentimos, la p�gina que solicit� no esta disponible con sus credenciales.');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('disable', NULL, NULL, 'Disable', '0', 0, '0', 'Deshabilitar');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('edit', NULL, NULL, 'Edit', '0', 0, '0', 'Editar');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('empty', NULL, NULL, 'Blank', '0', 0, '0', 'En blanco');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('enable', NULL, NULL, 'Enable', '0', 0, '0', 'Habilitar');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('enabled', NULL, NULL, 'Enabled', '0', 0, '0', 'Habilitado');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('disabled', NULL, NULL, 'Disabled', '0', 0, '0', 'Deshabilitado');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('end', NULL, NULL, 'End', '0', 0, '0', 'Finalizar');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('english', NULL, NULL, 'English', '0', 0, '0', 'Ingl�s');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('entityClass', NULL, NULL, 'Class', '0', 0, '0', 'Clase');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('entityName', NULL, NULL, 'Name', '0', 0, '0', 'Nombre');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('entityProperty', NULL, NULL, 'Property', '0', 0, '0', 'Propiedad');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('entityPropertyNewValue', NULL, NULL, 'New value', '0', 0, '0', 'Nuevo valor');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('entityPropertyOldValue', NULL, NULL, 'Old value', '0', 0, '0', 'Valor anterior');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('expiredToken', NULL, NULL, 'The token has expired', '0', 0, '0', 'El token ha expirado');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('export', NULL, NULL, 'Export', '0', 0, '0', 'Exportar');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('footer', NULL, NULL, '2019 MSPAS', '0', 0, '0', '2019 MSPAS');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('generate', NULL, NULL, 'Generate', '0', 0, '0', 'Generar');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('heading', NULL, NULL, 'Vector Control Activities', '0', 0, '0', 'Actividades de control de vectores');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('home', NULL, NULL, 'Start', '0', 0, '0', 'Inicio');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('invalidToken', NULL, NULL, 'The token is invalid', '0', 0, '0', 'El token es inv�lido');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ipaddress', NULL, NULL, 'IP address', '0', 0, '0', 'Direccion IP');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('language', NULL, NULL, 'Language', '0', 0, '0', 'Idioma/Language');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('lastAccess', NULL, NULL, 'Last access', '0', 0, '0', 'Ultimo acceso');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('lock', NULL, NULL, 'Lock', '0', 0, '0', 'Bloquear');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('locked', NULL, NULL, 'Locked', '0', 0, '0', 'Bloqueado');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('login', NULL, NULL, 'Login', '0', 0, '0', 'Ingresar');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('login.accountExpired', NULL, NULL, 'User account has expired', '0', 0, '0', 'Cuenta de usuario ha expirado!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('login.accountLocked', NULL, NULL, 'User account is locked', '0', 0, '0', 'Cuenta de usuario est� bloqueada!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('login.accountNotLocked', NULL, NULL, 'User account is not locked', '0', 0, '0', 'Cuenta de usuario est� desbloqueada!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('login.badCredentials', NULL, NULL, 'User name or password is incorred', '0', 0, '0', 'Nombre de usuario o contrase�a incorrectos!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('login.credentialsExpired', NULL, NULL, 'User credentials have expired', '0', 0, '0', 'Credenciales de usuario han expirado!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('login.forgot.password', NULL, NULL, 'Forgot password?', '0', 0, '0', 'Olvid� contrase�a?');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('login.maxSessionsOut', NULL, NULL, 'You have an active session! You can�t create another one!', '0', 0, '0', 'Tiene una sesi�n activa! No puede crear otra!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('login.message', NULL, NULL, 'Please enter your user name and password', '0', 0, '0', 'Por favor ingresar su nombre de usuario y contrase�a');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('login.password', NULL, NULL, 'Password', '0', 0, '0', 'Contrase�a');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('login.userDisabled', NULL, NULL, 'User is disabled', '0', 0, '0', 'Usuario esta inactivo!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('login.userEnabled', NULL, NULL, 'User is enabled', '0', 0, '0', 'Usuario esta activo!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('login.username', NULL, NULL, 'User name', '0', 0, '0', 'Nombre de usuario');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('logindate', NULL, NULL, 'Login date', '0', 0, '0', 'Fecha ingreso');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('logout', NULL, NULL, 'Logout', '0', 0, '0', 'Salir');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('logoutdate', NULL, NULL, 'Logout date', '0', 0, '0', 'Fecha salida');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('logouturl', NULL, NULL, 'Logout url', '0', 0, '0', 'URL salida');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('messageKey', NULL, NULL, 'Message key', '0', 0, '0', 'C�digo mensaje');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('modifiedBy', NULL, NULL, 'Modified by', '0', 0, '0', 'Modificado por');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('newResult', NULL, NULL, 'New result', '0', 0, '0', 'Nuevo resultado');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('noResults', NULL, NULL, 'There are no results', '0', 0, '0', 'No hay registros!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('not', NULL, NULL, 'Notification', '0', 0, '0', 'Notificaci�n');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('notenabled', NULL, NULL, 'Disabled', '0', 0, '0', 'Deshabilitado');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('notfound', NULL, NULL, 'Not found', '0', 0, '0', 'No encontrado');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('notfoundmessage', NULL, NULL, 'Sorry, the page you\'re looking for can\'t be found', '0', 0, '0', 'Lo sentimos, la p�gina que solicit� no pudo ser encontrada.');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('notlocked', NULL, NULL, 'Unlocked', '0', 0, '0', 'Desbloqueado');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ok', NULL, NULL, 'Accept', '0', 0, '0', 'Aceptar');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('opcionesCatalogo', NULL, NULL, 'Responses in this catalogue', '0', 0, '0', 'Respuestas en este cat�logo');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('options', NULL, NULL, 'Options', '0', 0, '0', 'Opciones');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('order', NULL, NULL, 'Order', '0', 0, '0', 'Ordenamiento');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('parameter', NULL, NULL, 'parameter', '0', 0, '0', 'Par�metro');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('pasive', NULL, NULL, 'disabled', '0', 0, '0', 'De baja');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('pasivo', NULL, NULL, 'canceled', '0', 0, '0', 'Anulado');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('pass.updated', NULL, NULL, 'Password updated', '0', 0, '0', 'Su contrase�a ha sido actualizada');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('password.repeat', NULL, NULL, 'Repeat Password', '0', 0, '0', 'Repita la contrase�a');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('Pattern.password.format', NULL, NULL, 'At least 8 characters combining uppercase, lowercase, numbers and special characters', '0', 0, '0', 'Al menos 8 caracteres combinando may�sculas, min�sculas, numeros y caracteres especiales');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('please.enter', NULL, NULL, 'Please enter', '0', 0, '0', 'Favor ingresar');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('process.errors', NULL, NULL, 'errors have occurred in the process ', '0', 0, '0', 'Han ocurrido errores en el proceso!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('process.success', NULL, NULL, 'the process has been successfully completed', '0', 0, '0', 'El proceso se ha completado exitosamente!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('process.wait', NULL, NULL, 'please wait', '0', 0, '0', 'Por favor espere..');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('profile', NULL, NULL, 'Profile', '0', 0, '0', 'Perfil');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('resetPassword', NULL, NULL, 'New password sent by e-mail', '0', 0, '0', 'Enviar nueva contrase�a por correo');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('rolAdded', NULL, NULL, 'Added role', '0', 0, '0', 'Rol agregado!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('rolAll', NULL, NULL, 'all roles are already added', '0', 0, '0', 'Todos los roles ya est�n agregados!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('rolDisabled', NULL, NULL, 'role is inactive', '0', 0, '0', 'Rol esta inactivo!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('rolEnabled', NULL, NULL, 'role is active', '0', 0, '0', 'Rol esta activo!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ROLE_ADMIN', NULL, NULL, 'administrator ', '0', 0, '0', 'Administrador');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ROLE_PASSWORD', NULL, NULL, 'Change password', '0', 0, '0', 'Cambio de contrase�a');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ROLE_REVIEW', NULL, NULL, 'View Data', '0', 0, '0', 'Visualizar');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ROLE_SPRAY', NULL, NULL, 'Spray', '0', 0, '0', 'Rociador');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ROLE_SUPER', NULL, NULL, 'Supervisor', '0', 0, '0', 'Supervisor');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ROLE_CENSUS', NULL, NULL, 'Census', '0', 0, '0', 'Censo');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('save', NULL, NULL, 'save', '0', 0, '0', 'Guardar');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('search', NULL, NULL, 'search', '0', 0, '0', 'Buscar');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('searchResult', NULL, NULL, 'search result', '0', 0, '0', 'Buscar resultado');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('seccatalogs', NULL, NULL, 'answers/response', '0', 0, '0', 'Respuestas');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('seccatalogsform', NULL, NULL, 'Answer/response management', '0', 0, '0', 'Gesti�n de respuestas');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('secondEntry', NULL, NULL, 'second entry', '0', 0, '0', 'Segunda entrada');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('seconds', NULL, NULL, 'seconds', '0', 0, '0', 'segundos');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('select', NULL, NULL, 'select', '0', 0, '0', 'Seleccionar');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('selected', NULL, NULL, 'selected', '0', 0, '0', 'Seleccionados');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('session', NULL, NULL, 'session ID', '0', 0, '0', 'Id de sesion');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('session.expiring', NULL, NULL, 'your session is about to expire', '0', 0, '0', 'Su sesi�n est� a punto de expirar!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('session.expiring.confirm', NULL, NULL, 'do you want to continue with your session?', '0', 0, '0', 'Quiere continuar con su sesi�n?');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('session.expiring.time', NULL, NULL, 'your session will close in�', '0', 0, '0', 'Su sesi�n se cerrar� en');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('session.keep', NULL, NULL, 'keep session', '0', 0, '0', 'Mantener sesi�n');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('spanish', NULL, NULL, 'spanish', '0', 0, '0', 'Espa�ol');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('title', NULL, NULL, 'IRS', '0', 0, '0', 'RRI');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('translation', NULL, NULL, 'translation', '0', 0, '0', 'Traducci�n');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('translations', NULL, NULL, 'translation of messages', '0', 0, '0', 'Traducci�n de mensajes');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('unlock', NULL, NULL, 'unlock', '0', 0, '0', 'Desbloquear');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('user.created', NULL, NULL, 'user created', '0', 0, '0', 'Usuario creado');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('user.updated', NULL, NULL, 'user updated', '0', 0, '0', 'Usuario actualizado');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('usercred', NULL, NULL, 'password expired', '0', 0, '0', 'Contrase�a vencida');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('userdesc', NULL, NULL, 'user description', '0', 0, '0', 'Descripci�n');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('useremail', NULL, NULL, 'user email', '0', 0, '0', 'Correo');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('userexp', NULL, NULL, 'expired account', '0', 0, '0', 'Cuenta vencida');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('userlock', NULL, NULL, 'locked', '0', 0, '0', 'Bloqueado');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('username', NULL, NULL, 'username', '0', 0, '0', 'Usuario');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('userroles', NULL, NULL, 'roles', '0', 0, '0', 'Roles');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('users', NULL, NULL, 'users', '0', 0, '0', 'Usuarios');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('usuarioAdded', NULL, NULL, 'user added', '0', 0, '0', 'Usuario agregado!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('usuarioAll', NULL, NULL, 'all users are already added', '0', 0, '0', 'Todos los usuarios ya est�n agregados!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('usuarioDisabled', NULL, NULL, 'user is inactivated', '0', 0, '0', 'Usuario est� inactivo!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('usuarioEnabled', NULL, NULL, 'user is activated', '0', 0, '0', 'Usuario est� activo!');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('value', NULL, NULL, 'value', '0', 0, '0', 'Valor');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('welcome', NULL, NULL, 'welcome', '0', 0, '0', 'Bienvenido ');


INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('areas', NULL, NULL, 'Areas', '0', 0, '0', 'Areas de salud');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('districts', NULL, NULL, 'Districts', '0', 0, '0', 'Distritos');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('localities', NULL, NULL, 'Localities', '0', 0, '0', 'Localidades');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('area', NULL, NULL, 'Area', '0', 0, '0', 'Area de salud');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('district', NULL, NULL, 'District', '0', 0, '0', 'Distrito');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('locality', NULL, NULL, 'Locality', '0', 0, '0', 'Localidad');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('class org.clintonhealthaccess.vca.domain.Area', NULL, NULL, 'Area', '0', 0, '0', 'Area');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('class org.clintonhealthaccess.vca.domain.Distrito', NULL, NULL, 'District', '0', 0, '0', 'Distrito');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('class org.clintonhealthaccess.vca.domain.Localidad', NULL, NULL, 'Locality', '0', 0, '0', 'Localidad');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('class org.clintonhealthaccess.vca.domain.Household', NULL, NULL, 'House', '0', 0, '0', 'Vivienda');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ident', NULL, NULL, 'Identifier', '0', 0, '0', 'Identificador');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('code', NULL, NULL, 'Code', '0', 0, '0', 'C�digo');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('name', NULL, NULL, 'Name', '0', 0, '0', 'Nombre');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('location', NULL, NULL, 'Location', '0', 0, '0', 'Ubicaci�n');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('latitude', NULL, NULL, 'Latitude', '0', 0, '0', 'Latitud');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('longitude', NULL, NULL, 'Longitude', '0', 0, '0', 'Longitud');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('population', NULL, NULL, 'Population', '0', 0, '0', 'Poblaci�n');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('obs', NULL, NULL, 'Notes', '0', 0, '0', 'Observaciones');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('orgunit', NULL, NULL, 'Org Units', '0', 0, '0', 'Unidades Organizativas');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('sysopts', NULL, NULL, 'System options', '0', 0, '0', 'Opciones sistema');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('resources', NULL, NULL, 'Human resources', '0', 0, '0', 'Recursos humanos');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('parameters', NULL, NULL, 'Parameters', '0', 0, '0', 'Par�metros');


INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('locselect', NULL, NULL, 'Click on the map or drag the marker', '0', 0, '0', 'Hacer click en el mapa o arrastrar el marcador');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('505', NULL, NULL, 'Something�s wrong!', '0', 0, '0', 'Ha ocurrido un error!');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('userlocs', NULL, NULL, 'User localities', '0', 0, '0', 'Localidades del usuario');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('class org.clintonhealthaccess.vca.domain.relationships.UsuarioLocalidad', NULL, NULL, 'Locality', '0', 0, '0', 'Localidad');

INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('censustakers', NULL, NULL, 'Census takers', '0', 0, '0', 'Censadores');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('sprayers', NULL, NULL, 'Sprayers', '0', 0, '0', 'Rociadores');
INSERT INTO `messages` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('supervisors', NULL, NULL, 'Supervisors', '0', 0, '0', 'Supervisores');



