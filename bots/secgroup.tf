resource "openstack_networking_secgroup_v2" "mattermost-server" {
  name        = "mattermost-bots"
  description = "mattermost-bots open 80"
}

resource "openstack_networking_secgroup_rule_v2" "mattermost-bot-api" {
  direction         = "ingress"
  ethertype        = "IPv4"
  protocol         = "tcp"
  remote_ip_prefix = "0.0.0.0/0"
  security_group_id = openstack_networking_secgroup_v2.mattermost-server.id
  port_range_min    = 80
  port_range_max    = 80
  description       = "Ingress api traffic allowed from the World"
}
