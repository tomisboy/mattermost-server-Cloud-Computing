resource "openstack_networking_secgroup_v2" "mattermost-server" {
  name        = "mattermost-bots"
  description = "mattermost-bots open 8080"
}

resource "openstack_networking_secgroup_rule_v2" "mattermost-bot-api" {
  direction         = "ingress"
  ethertype        = "IPv4"
  protocol         = "tcp"
  remote_ip_prefix = "0.0.0.0/0"
  security_group_id = openstack_networking_secgroup_v2.mattermost-server.id
  port_range_min    = 8080
  port_range_max    = 8080
  description       = "Ingress api traffic allowed from the World"
}
