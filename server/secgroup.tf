resource "openstack_networking_secgroup_v2" "mattermost-server" {
  name        = "mattermost-server-allow-http/https"
  description = "mattermost-server-allow-http/https"
}

resource "openstack_networking_secgroup_rule_v2" "mattermost-server-http" {
  direction         = "ingress"
  ethertype        = "IPv4"
  protocol         = "tcp"
  remote_ip_prefix = "0.0.0.0/0"
  security_group_id = openstack_networking_secgroup_v2.mattermost-server.id
  port_range_min    = 80
  port_range_max    = 80
  description       = "Ingress HTTP traffic allowed from the World"
}

resource "openstack_networking_secgroup_rule_v2" "mattermost-server-https" {
  direction         = "ingress"
  ethertype        = "IPv4"
  protocol         = "tcp"
  remote_ip_prefix = "0.0.0.0/0"
  security_group_id = openstack_networking_secgroup_v2.mattermost-server.id
  port_range_min    = 443
  port_range_max    = 443
  description       = "Ingress HTTPS traffic allowed from the World"
}

